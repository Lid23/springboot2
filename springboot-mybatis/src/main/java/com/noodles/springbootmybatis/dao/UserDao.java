package com.noodles.springbootmybatis.dao;

import com.noodles.springbootmybatis.bean.User;

import java.util.List;

/**
 * @program: springboot
 * @description: 用户表dao层
 * @author: Eric
 * @create: 2018-11-27 17:19
 **/
public interface UserDao {

    int insertUser(User user);

    List<User> getUsers();

}
