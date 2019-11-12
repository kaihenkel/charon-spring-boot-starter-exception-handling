package com.example.interseptor;

import com.github.mkopylec.charon.forwarding.interceptors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

public class TestForwardingInterseptor implements RequestForwardingInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TestForwardingInterseptor.class);

    @Override
    public HttpResponse forward(HttpRequest request, HttpRequestExecution execution) {
        if (request.getURI().getPath().startsWith("/testerror")) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }

        return execution.execute(request);
    }

    @Override
    public RequestForwardingInterceptorType getType() {
        return new RequestForwardingInterceptorType(
                RequestForwardingInterceptorType.AUTHENTICATOR.getOrder() - 50
        );
    }
}
