package com.sz.wy.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sz.wy.api.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class HelloController {

    @Reference
    HelloService helloService;

    @RequestMapping("/hello")
    public String hello(){
        return "consumer: "+helloService.hello();
    }

}
