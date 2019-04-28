package com.noodles.springbootjpa.repository;

import com.noodles.springbootjpa.bean.AuthorBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorBean, Integer> {

    AuthorBean findByUsername(String name);

    List<AuthorBean> findByUsernameContaining(String name);

}
