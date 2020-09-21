package com.noodles.springbootjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noodles.springbootjpa.bean.UserBean;
import com.noodles.springbootjpa.repository.UserJPA;

/**
* @Description:
* @Author: Eric
* @Date: 2019/4/28
*/
@RestController
public class UserController {

    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/list")
    public List<UserBean> list() {
        return userJPA.findAll();
    }

    @RequestMapping(value = "/add")
    public String add() {
        UserBean userBean = new UserBean();
        userBean.setName("测试");
        userBean.setAddress("测试地址");
        userBean.setAge(21);
        userJPA.save(userBean);
        return "用户信息添加成功";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long userId) {
        UserBean userBean = new UserBean();
        userBean.setId(userId);
        userJPA.delete(userBean);
        return "用户信息删除成功";
    }

    @RequestMapping(value = "/age")
    public List<UserBean> age() {
        return userJPA.nativeQuery(20);
    }

    /**
     * 根据条件自定义编写删除SQL
     *
     * @return
     */
    @RequestMapping(value = "/deleteWhere")
    public String deleteWhere() {
        userJPA.deleteQuery("admin", "123456");
        return "自定义SQL删除数据成功";
    }

    /**
     * 分页查询测试
     *
     * @param page 传入页码，从1开始
     * @return
     */
    @RequestMapping(value = "/cutpage")
    public List<UserBean> cutPage(int page) {
        UserBean user = new UserBean();
        user.setSize(2);
        user.setSord("desc");
        user.setPage(page);

        //获取排序对象
        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(user.getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        //设置排序对象参数
        Sort sort = Sort.by(sort_direction, user.getSidx());
        //创建分页对象
        PageRequest pageRequest = PageRequest.of(user.getPage() - 1, user.getSize(), sort);
        //执行分页查询
        return userJPA.findAll(pageRequest).getContent();
    }
}
