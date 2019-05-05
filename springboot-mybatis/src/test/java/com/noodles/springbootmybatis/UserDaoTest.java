package com.noodles.springbootmybatis;

import com.noodles.springbootmybatis.bean.User;
import com.noodles.springbootmybatis.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: springboot
 * @description: 用户测试
 * @author: Eric
 * @create: 2018-11-27 17:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void testUser() throws Exception {
        User user = new User();
        user.setUsername("test6");
        user.setPsw("test6");

        final int row1 = userDao.insertUser(user);
        System.out.println("[添加结果] " +  row1);

    }

    @Test
    public void testGetUsers(){
        userDao.getUsers();
    }


}
