package com.company.firesale.controller;

import com.company.firesale.json_classes.TestJsonClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/{testString}")
    public TestJsonClass test(@PathVariable String testString) {
        return new TestJsonClass(testString);
    }

}
