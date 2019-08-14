package com.noodles.springbootmybatis.dao;

import com.noodles.springbootmybatis.bean.StudentBean;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: springboot
 * @description: 用户表dao层
 * @author: Eric
 * @create: 2018-11-27 17:19
 **/
public interface StudentDao extends Mapper<StudentBean> {


}
