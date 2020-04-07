package com.cx.springbootmybatis.controller;

import com.cx.springbootmybatis.entity.User;
import com.cx.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String getUser(@PathVariable int id){
       User user = userService.getById(id);
       return user.toString();
    }

    @RequestMapping("insert")
    public void insert(String username,String password,String realName){
        User user = new User(username,password,realName);
        userService.insert(user);
    }

    @RequestMapping("update")
    public void update(){
        User user = userService.getById(3);
        user.setRealName("李思13");
        userService.update(user);
    }

}
