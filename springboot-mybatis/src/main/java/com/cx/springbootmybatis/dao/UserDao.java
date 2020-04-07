package com.cx.springbootmybatis.dao;

import com.cx.springbootmybatis.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getById(int id);

    int insert(User user);

    int update(User user);
}
