package com.noodles.springbootjpa.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class BookBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinTable(name = "book_author", joinColumns = {
            @JoinColumn(name = "book_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "author_id", referencedColumnName = "id")})
    private Set<AuthorBean> authorBeanSet;

    public BookBean() {
        super();
    }

    public BookBean(String name) {
        super();
        this.name = name;
        this.authorBeanSet = new HashSet<>();
    }

    public BookBean(String name, Set<AuthorBean> authorBeanSet) {
        super();
        this.name = name;
        this.authorBeanSet = authorBeanSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuthorBean> getAuthorBeanSet() {
        return authorBeanSet;
    }

    public void setAuthorBeanSet(Set<AuthorBean> authorBeanSet) {
        this.authorBeanSet = authorBeanSet;
    }


}
