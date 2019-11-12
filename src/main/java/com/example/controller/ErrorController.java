package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ErrorController extends BasicErrorController {

    private final ErrorAttributes errorAttributes;

    public ErrorController(ErrorAttributes errorAttributes,
                           List<ErrorViewResolver> errorViewResolvers,
                           ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
        this.errorAttributes = errorAttributes;
    }

    @Override
    protected HttpStatus getStatus(HttpServletRequest request) {
        HttpStatus status = super.getStatus(request);
        if (!status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            return status;
        }

        Throwable reason = errorAttributes.getError(new ServletWebRequest(request));
        if (reason instanceof ResponseStatusException) {
            return ((ResponseStatusException) reason).getStatus();
        }
        return status;
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        Map<String, Object> attributes =  super.getErrorAttributes(request, includeStackTrace);
        attributes.put("status", getStatus(request).value());
        return attributes;
    }
}
