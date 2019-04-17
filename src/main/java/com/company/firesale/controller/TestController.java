package com.company.firesale.controller;

import com.company.firesale.json_classes.TestJsonClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class TestController {

    @GetMapping("/test/{testString}")
    public TestJsonClass test(@PathVariable String testString) {
        return new TestJsonClass(testString);
    }

    @GetMapping("/login/success")
    public boolean loginSucces() {
        return true;
    }

}
