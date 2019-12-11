package com.noodles.dubboprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @filename HelloAction
 * @description
 * @author 巫威
 * @date 2019/12/10 10:28
 */

@RestController
@RequestMapping(value = "/")
public class HelloAction {

	@GetMapping(value = "hello")
	public String hello(String msg){
		return "hello " + msg;
	}
}
