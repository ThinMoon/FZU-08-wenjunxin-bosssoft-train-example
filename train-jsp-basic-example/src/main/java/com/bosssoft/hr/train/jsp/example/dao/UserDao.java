package com.bosssoft.hr.train.jsp.example.dao;

import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;

import java.util.List;

/**
 * @description: 该层的方法只能出现对应数据层面的操作的不可以出现业务描述
 * 例如 check，authentication
 * @author: Administrator
 * @create: 2020-05-30 10:38
 * @since
 **/
public interface UserDao {
    /**
     *  插入记录
     * @param user
     * @return 返回影响的行数
     */
    int insert(User user);
    int deleteById(Integer id);
    int update(User user);
    List<User> queryByCondition(Query queryCondition);
}

