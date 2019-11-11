package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MessageController {

    @RequestMapping("/message*")
    public String message() {
        throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
    }
}
