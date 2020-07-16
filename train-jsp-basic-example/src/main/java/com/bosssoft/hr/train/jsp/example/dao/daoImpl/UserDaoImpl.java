package com.bosssoft.hr.train.jsp.example.dao.daoImpl;

import com.bosssoft.hr.train.database.DBUtil;
import com.bosssoft.hr.train.jsp.example.dao.UserDao;
import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Administrator
 * @create: 2020-05-30 10:42
 * @since
 **/
@Slf4j
public class UserDaoImpl implements UserDao {
    /**
     * 插入分为 有id 和 无id
     * @param user
     * @return
     */
    @Override
    public int insert(User user) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        //拼接sql
        String sql;
        if (user != null) {

            try {
                //连接数据库
                conn = DBUtil.getConn();

                if (user.getId() != null) {
                    sql = "insert into t_user(user_id, user_name, user_code, user_password) values (?,?, ?, ?)";
                    //获取statement
                    preparedStatement = conn.prepareStatement(sql);

                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getCode());
                    preparedStatement.setString(4, user.getPassword());

                } else {
                    sql = "insert into t_user(user_name, user_code, user_password) values (?, ?, ?)";
                    //获取statement
                    preparedStatement = conn.prepareStatement(sql);

                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getCode());
                    preparedStatement.setString(3, user.getPassword());
                }

                int result = preparedStatement.executeUpdate();
                return result;

            } catch (SQLException e) {
                log.info("数据库插入异常：{}", e.getMessage());
                return 0;

            } finally {
                DBUtil.close(conn, preparedStatement, null);
            }
        }

        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        //拼接sql
        String sql = "delete from t_user where user_id = ?";

        try {
            //连接数据库
            conn = DBUtil.getConn();
            //获取statement
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();
            return result;

        } catch (SQLException e) {
            log.info("数据库插入异常：{}", e.getMessage());
            return 0;
        } finally {
            DBUtil.close(conn, preparedStatement, null);
        }

    }

    /**
     * 使用user_id修改
     *
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        //拼接sql
        String sql = "update t_user set user_name = ?, user_password = ?, user_code = ? where user_id = ?";

        try {
            //连接数据库
            conn = DBUtil.getConn();
            //获取statement
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getCode());
            preparedStatement.setInt(4, user.getId());

            int result = preparedStatement.executeUpdate();
            return result;

        } catch (SQLException e) {
            log.info("数据库插入异常：{}", e.getMessage());
            return 0;
        } finally {
            DBUtil.close(conn, preparedStatement, null);
        }
    }

    @Override
    public List<User> queryByCondition(Query queryCondition) {

        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //拼接sql
        String sql;

        try {
            //连接数据库
            conn = DBUtil.getConn();
            //获取statement
            if(!queryCondition.getIsAll()) {
                sql = "select * from t_user where user_name = ? and user_password = ?";
                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, queryCondition.getName());
                preparedStatement.setString(2, queryCondition.getPassword());
            } else {
                sql = "select * from t_user ";
                preparedStatement = conn.prepareStatement(sql);
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String user_name = resultSet.getString("user_name");
                String user_code = resultSet.getString("user_code");
                String user_password = resultSet.getString("user_password");
                int user_id = resultSet.getInt("user_id");

                User user = new User(user_id, user_name, user_code, user_password);
                users.add(user);

            }
            return users;

        } catch (SQLException e) {
            log.info("数据库查询异常：{}", e.getMessage());
            return users;

        } finally {
            DBUtil.close(conn, preparedStatement, resultSet);
        }

    }
}

