package com.cx.springbootmybatis.controller;

import com.cx.springbootmybatis.entity.Teacher;
import com.cx.springbootmybatis.entity.User;
import com.cx.springbootmybatis.service.TeacherService;
import com.cx.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("getTeacher/{id}")
    public String getTeacher(@PathVariable int id){
        Teacher teacher = teacherService.getById(id);
       return teacher.toString();
    }

    @RequestMapping("insert")
    public void insert(String teacherName,Integer teacherAge){
        Teacher teacher = new Teacher(teacherName,teacherAge);
        teacherService.insert(teacher);
    }

    @RequestMapping("update")
    public void update(){
        Teacher teacher = teacherService.getById(2);
        teacher.setTeacherAge(30);
        teacherService.update(teacher);
    }

}
