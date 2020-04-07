package com.example.demo.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testControl")
public class TestController {

    @RequestMapping("/test1")
    public String test(){
        return "你好，我来了";
    }

}
