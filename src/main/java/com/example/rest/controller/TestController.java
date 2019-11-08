package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TestController {

    @RequestMapping("/message*")
    public String testme() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
