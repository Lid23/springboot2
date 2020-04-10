package com.noodles.springbootavoidrepeatsubmission.controller;

import com.noodles.springbootavoidrepeatsubmission.annotation.CacheLock;
import com.noodles.springbootavoidrepeatsubmission.annotation.CacheParam;
import com.noodles.springbootavoidrepeatsubmission.annotation.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description: BookController
 * @author: Eric
 * @create: 2019-06-14 15:53
 **/
@RestController
@RequestMapping("/books")
public class BookController {

    /*@LocalLock(key = "book:arg[0]")
    @GetMapping
    public String query(@RequestParam String token) {
        return "success - " + token;
    }*/

    @CacheLock(prefix = "books")
    @GetMapping
    public String query1(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }
}
