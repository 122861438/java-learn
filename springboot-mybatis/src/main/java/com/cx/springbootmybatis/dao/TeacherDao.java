package com.cx.springbootmybatis.dao;

import com.cx.springbootmybatis.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao {

    Teacher getById(int id);

    int insert(Teacher teacher);

    int update(Teacher teacher);
}
