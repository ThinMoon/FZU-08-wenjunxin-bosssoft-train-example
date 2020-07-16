package com.bosssoft.hr.train.database;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * @author 温俊欣
 * 数据库工具类
 * final 保护工具类不被继承
 */
@Slf4j
public final class DBUtil {



    private final static String URL = "jdbc:MySQL://39.107.43.138:3306/bosssoft?useUnicode=true&useSSL=false&characterEncoding=utf-8";
    private final static String USERNAME = "root";
    private final static String PWD = "root1234";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";


    /**
     * 预先加载驱动
     */
    static {
        // 1.加载驱动
        try {
            Class.forName(DBUtil.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保护工具类不被实现
     */
    private DBUtil() { }

    /**
     * 获取连接器
     * @return
     */
    public static Connection getConn() {

        try {
            // 2.连接数据库
            return DriverManager.getConnection(DBUtil.URL, DBUtil.USERNAME, DBUtil.PWD);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("数据库连接失败");
        }

        return null;
    }

    /**
     * 关闭数据库连接
     * @param conn
     * @param st
     * @param res
     */
    public static void close(Connection conn, Statement st, ResultSet res) {
        // 5.释放
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (st != null) {
            try {

                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



}
