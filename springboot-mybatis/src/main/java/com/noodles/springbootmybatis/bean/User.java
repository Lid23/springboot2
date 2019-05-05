package com.noodles.springbootmybatis.bean;

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

    private String psw;



    public User() {
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

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
