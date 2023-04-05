package com.denis.task2.controller;

import com.denis.task2.service.FTPService;
import com.denis.task2.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StartController {

    private final FTPService ftpService;

    @Autowired
    public StartController(FTPService ftpService) {
        this.ftpService = ftpService;
    }

    @GetMapping("/photos")
    public List<Response> getApplications()
    {
        return  ftpService.getInfo();
    }

}
