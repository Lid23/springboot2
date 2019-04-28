package com.noodles.springbootjpa.repository;

import com.noodles.springbootjpa.bean.BookBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookBean, Integer> {

    BookBean findByName(String name);

    List<BookBean> findByNameContaining(String name);

}
