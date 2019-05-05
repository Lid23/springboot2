package com.noodles.springbootmybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noodles.springbootmybatis.bean.City;
import com.noodles.springbootmybatis.dao.CityDao;
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
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class CityDaoTest {

    private static final Logger log = LoggerFactory.getLogger(CityDaoTest.class);

    @Autowired
    private CityDao cityDao;

    @Test
    public void testCity(){

        City city = new City();
        city.setCityName("兴宁");
        city.setDescription("华侨之乡");
        int rows = cityDao.insert(city);
        System.out.println("rows " + rows);

        System.out.println(cityDao.selectAll());
    }

    @Test
    public void testPagehelp(){
        // 分页 + 排序 this.cityDao.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<City> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").
                doSelectPageInfo(() -> this.cityDao.selectAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(2, 10).setOrderBy("id desc");
        final PageInfo<City> cityPageInfo = new PageInfo<>(this.cityDao.selectAll());
        log.info("[普通写法] - [{}]", cityPageInfo);
    }

}
