package com.example.demo.controller;

import com.example.demo.service.ManagerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class ManagerController {
    @Resource
    private ManagerService managerService;
    
}
