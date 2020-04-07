package com.cx.springbootmybatis.service;

import com.cx.springbootmybatis.dao.UserDao;
import com.cx.springbootmybatis.entity.Teacher;
import com.cx.springbootmybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TeacherService teacherService;

    public User getById(int id){
        return userDao.getById(id);
    }

    public void insert(User user){
        int i = userDao.insert(user);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void update(User user){

        int i = userDao.update(user);

        Teacher teacher = teacherService.getById(2);
        teacher.setTeacherAge(31);

        teacherService.update(teacher);

    }


}
