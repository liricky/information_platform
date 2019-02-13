package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class HelloController {

    @ResponseBody
    @RequestMapping("/user")
    public String hello(){
        return "Hello World!";
    }

}
