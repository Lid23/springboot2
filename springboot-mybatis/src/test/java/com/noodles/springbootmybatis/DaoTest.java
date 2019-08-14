package com.noodles.springbootmybatis;

import com.noodles.springbootmybatis.bean.StudentBean;
import com.noodles.springbootmybatis.dao.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: springboot
 * @description: dao测试
 * @author: Eric
 * @create: 2018-11-27 17:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootMybatisApplication.class})
public class DaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void testStudentDao() throws Exception {
        List<StudentBean> studentBeanList = studentDao.selectAll();
        System.out.println(studentBeanList.size());

    }

}
