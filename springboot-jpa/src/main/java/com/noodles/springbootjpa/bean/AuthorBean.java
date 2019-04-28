package com.noodles.springbootjpa.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "author")
public class AuthorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @ManyToMany(mappedBy = "authorBeanSet", fetch= FetchType.EAGER)
    private Set<BookBean> bookBeanSet;

    public AuthorBean() {
        super();
    }

    public AuthorBean(String username) {
        super();
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<BookBean> getBookBeanSet() {
        return bookBeanSet;
    }

    public void setBookBeanSet(Set<BookBean> bookBeanSet) {
        this.bookBeanSet = bookBeanSet;
    }
}
