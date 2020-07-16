package com.bosssoft.hr.train.jsp.example.controller;

import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;

import java.util.List;

/**
 * @description: 如果 处理了注解就可以考虑映射到一个Controller多个方法
 * 这个类不需要实现吧 这种时间难度度考虑时间关系只是给出了框架示例
 * 注意这里只是一个类似spring的框架架构演示，实际作业 需要 针对增删除改查开发多个
 * servlet
 * @author: Administrator
 * @create: 2020-05-29 14:07
 * @since
 **/
public class UserController_NotNeedImplement implements UserApi {

    @Override
    public String save(User user) {
        return null;
    }

    @Override
    public String remove(User user) {
        return null;
    }

    @Override
    public String update(User user) {
        return null;
    }

    @Override
    public List<User> query(Query condition) {
        return null;
    }
}

