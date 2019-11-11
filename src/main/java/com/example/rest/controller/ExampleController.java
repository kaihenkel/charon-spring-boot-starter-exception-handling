package com.example.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {

    @RequestMapping("/example")
    public String example() {
        return "example";
    }
}
