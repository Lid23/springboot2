package com.noodles.springbootredis.bean;

import java.io.Serializable;

/**
 * @program: springboot
 * @description: 用户类
 * @author: Eric
 * @create: 2018-11-27 17:17
 **/

public class User implements Serializable {

    private static final long serialVersionUID = 8655851615465363473L;

    private Long id;

    private String username;

    private String password;



    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
