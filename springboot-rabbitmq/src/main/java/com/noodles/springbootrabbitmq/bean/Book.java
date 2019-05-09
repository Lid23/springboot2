package com.noodles.springbootrabbitmq.bean;

import java.io.Serializable;

/**
 * @program: springboot2
 * @description: Book类
 * @author: Eric
 * @create: 2019-05-08 15:33
 **/
public class Book implements Serializable {

    private static final long serialVersionUID = -1;

    private String id;

    private String name;

    public Book() {
    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
