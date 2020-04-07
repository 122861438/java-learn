package com.sz.wy.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sz.wy.api.service.HelloService;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Service
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        try {
            System.out.println("开始调用provider");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("被调用了");
        return "provider111";
    }
}
