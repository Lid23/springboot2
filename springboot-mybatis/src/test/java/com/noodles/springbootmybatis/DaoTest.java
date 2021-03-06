package com.noodles.springbootmybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.noodles.springbootmybatis.bean.StudentBean;
import com.noodles.springbootmybatis.dao.MybatisDao;
import com.noodles.springbootmybatis.dao.StudentDao;
import com.noodles.springbootmybatis.util.JsonUtil;

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

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private MybatisDao mybatisDao;

    @Test
    public void testStudentDao() {
        List<StudentBean> studentBeanList = studentDao.selectAll();
        System.out.println(JsonUtil.toJson(studentBeanList));

        StudentBean studentBean = studentDao.getStudentBySno("101");
        System.out.println(JsonUtil.toJson(studentBean));

    }

    @Test
    public void testSqlSession(){
        /*SqlSession sqlSession = sqlSessionFactory.openSession();
        MybatisDao mybatisDao = sqlSession.getMapper(MybatisDao.class);*/

        List<StudentBean> studentBeanList = mybatisDao.getStudentList();

        System.out.println(JsonUtil.toJson(studentBeanList));

        //sqlSession.close();

    }

}
