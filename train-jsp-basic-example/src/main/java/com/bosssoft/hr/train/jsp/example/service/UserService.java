package com.bosssoft.hr.train.jsp.example.service;

import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;

import java.util.List;

/**
 * @description: 用户服务的接口
 * @author: Administrator
 * @create: 2020-05-30 10:17
 * @since
 **/
public interface UserService {
    boolean save(User user);

    boolean remove(Integer id);

    boolean update(User user);

    List<User> queryByCondition(Query queryCondition);

    /**
     * 给 LoginController 调用
     *
     * @param user
     * @return
     */
    boolean authentication(User user);

}

