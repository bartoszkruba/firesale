package com.company.firesale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/")
public class TestController {

    @GetMapping("/admintest")
    public String adminTest() {
        return "This is admin route";
    }

    @GetMapping("/usertest")
    public String userTest() {
        return "This is user route";
    }

}
