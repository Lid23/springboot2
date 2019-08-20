package com.noodles.springbootmybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.noodles.springbootmybatis.bean.StudentBean;

/**
 * @program: springboot
 * @description: 用户表dao层
 * @author: Eric
 * @create: 2018-11-27 17:19
 **/
public interface MybatisDao {

	List<StudentBean> getStudentList();

}
