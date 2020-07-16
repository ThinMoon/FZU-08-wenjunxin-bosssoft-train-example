package com.bosssoft.hr.train.jsp.example.service.serviceImpl;

import com.bosssoft.hr.train.jsp.example.dao.UserDao;
import com.bosssoft.hr.train.jsp.example.dao.daoImpl.UserDaoImpl;
import com.bosssoft.hr.train.jsp.example.exception.BusinessException;
import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;
import com.bosssoft.hr.train.jsp.example.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Administrator
 * @create: 2020-05-30 10:24
 * @since
 **/

public class UserServiceImpl implements UserService {
    @Override
    public boolean save(User user) {
        try {
            // do you logic
            UserDao userDao = new UserDaoImpl();
            int result = userDao.insert(user);
            return result > 0;
        }catch (Exception ex){
            throw new BusinessException("10001",ex.getMessage(),ex);
        }

    }

    @Override
    public boolean remove(Integer id) {

        try {
            // do you logic
            UserDao userDao = new UserDaoImpl();
            int result = userDao.deleteById(id);
            return result > 0;
        }catch (Exception ex){
            throw new BusinessException("10001",ex.getMessage(),ex);
        }

    }

    @Override
    public boolean update(User user) {
        try {
            // do you logic
            UserDao userDao = new UserDaoImpl();
            int result = userDao.update(user);
            return result > 0;
        }catch (Exception ex){
            throw new BusinessException("10001",ex.getMessage(),ex);
        }
    }

    @Override
    public List<User> queryByCondition(Query queryCondition) {

        UserDao userDao = new UserDaoImpl();

        if (queryCondition != null) {
            return userDao.queryByCondition(queryCondition);
        }
        return new ArrayList<User>();
    }

    @Override
    public boolean authentication(User user) {

        UserDao userDao = new UserDaoImpl();
        if (user != null) {

            Query query = new Query(user.getName(), user.getPassword(), false);
            //查询账号 密码 存在返回true
            List<User> users = userDao.queryByCondition(query);
            if(users != null && !users.isEmpty()) {
                return true;
            }
        }

        return false;
    }
}

