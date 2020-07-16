package com.bosssoft.hr.train.jsp.example.controller;

import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;

import java.util.List;

/**
 * @param
 * @description:
 * @author: Administrator
 * @create: 2020-05-29 14:06
 * @since
 **/
public interface UserApi {
    /**
     *
     * @param user
     * @return
     */
    String save(User user);

    /**
     *
     * @param user
     * @return
     */
    String remove(User user);

    /**
     *
     * @param user
     * @return
     */
    String update(User user);

    /**
     *
     * @param condition
     * @return
     */
    List<User> query(Query condition);
}

