package com.bosssoft.hr.train.annotation;

import com.bosssoft.hr.train.database.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author 温俊欣
 * 解析注解 继承该类的子类可以使用注解开发
 */
@Slf4j
public abstract class BaseModel {

    /**
     * 表名
     */
    private String tableName = null;

    /**
     * 主键
     */
    private String primaryKey = null;

    /**
     * 所有列名
     */
    private List<String> columns = new LinkedList<>();

    /**
     * 数据库列名到字段的映射
     */
    private Map<String, String> colToField = new HashMap<>();


    /**
     * 解析注解
     *
     * @return
     */
    public Map<String, String> analysisAnnotation() {
        //初始化map集合 LinkedHashMap保证数据是有序的
        Map<String, String> map = new HashMap<>(20);
        try {
            //获取继承该类的子类的反射对象
            Class<? extends BaseModel> cls = this.getClass();
            //判断@Table是否存在于该类中
            boolean isTable = cls.isAnnotationPresent(Table.class);
            if (isTable) {
                //注解存在 获取注解 读取value值 获取表名
                tableName = cls.getAnnotation(Table.class).value();
            } else {
                //注解不存在
                log.error("@Table不存在！");
                return null;
            }

            //获取类中的所有字段
            Field[] fields = cls.getDeclaredFields();
            //遍历每个字段
            for (Field field : fields) {
                //设置访问属性 去除私有权限 不去除将无法操作
                field.setAccessible(true);
                //判断字段是否有@Id注解
                boolean isId = field.isAnnotationPresent(Id.class);
                //判断字段是否有@Column注解
                boolean isColumn = field.isAnnotationPresent(Column.class);

                if (isId) {
                    //存在@Id注解 该字段为主键 获取注解 获取主键名
                    primaryKey = field.getAnnotation(Id.class).value();
                    //设置主键名 主键值的映射
                    map.put(primaryKey, field.get(this).toString());
                    //设置列名与字段名的映射
                    colToField.put(primaryKey, field.getName());


                } else if (isColumn) {
                    //存在@Column注解 该字段为普通列 获取注解 获取列名
                    String column = field.getAnnotation(Column.class).value();
                    //设置普通列与值的映射
                    map.put(column, field.get(this).toString());
                    //存入列名
                    columns.add(column);
                    //设置列名与字段名的映射
                    colToField.put(column, field.getName());

                }
            }
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            log.error("注解解析失败！");
            return null;
        }

    }


    /**
     * 保存数据
     * @return
     */
    public int save() {
        //获取映射
        Map<String, String> map = analysisAnnotation();
        //获取所有列名 拼接
        StringBuilder columnsSb = new StringBuilder();
        //拼接对应的占位符
        StringBuilder preSql = new StringBuilder();

        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()) {
            String column = iterator.next();
            columnsSb.append(column);
            preSql.append("?");
            //最后一项不需要拼接逗号
            if(iterator.hasNext()) {
                columnsSb.append(",");
                preSql.append(",");
            }
        }
        //拼接sql语句
        String sql = "insert into " + tableName + "(" +primaryKey+"," + columnsSb + ")" + "values" + "(" + "?," + preSql + ")";
        Connection conn = null;
        try {
            //连接数据库
            conn = DBUtil.getConn();
            //获取preparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //给占位符赋值

            preparedStatement.setInt(1,Integer.parseInt(map.get(primaryKey)));
            int index = 2;
            iterator = columns.iterator();
            while (iterator.hasNext()) {
                String column = iterator.next();
                String value = map.get(column);
                preparedStatement.setString(index++, value);
            }
            //执行语句
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("sql语句错误");
        }
        return 0;
    }


    /**
     * 修改数据
     */
    public int update() {
        //获取映射
        Map<String, String> map = analysisAnnotation();
        //获取所有列名 拼接
        StringBuilder columnsSb = new StringBuilder();
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()) {
            String column = iterator.next();
            columnsSb.append(column + " = ? ");
            //最后一项不需要拼接逗号
            if(iterator.hasNext()) {
                columnsSb.append(",");
            }
        }
        //拼接sql语句
        String sql = "update " + tableName + " set " + columnsSb + " where " + primaryKey + " = ? ";
        Connection conn = null;
        try {
            //连接数据库
            conn = DBUtil.getConn();
            //获取preparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //给占位符赋值
            int index = 1;
            iterator = columns.iterator();
            while (iterator.hasNext()) {
                String column = iterator.next();
                String value = map.get(column);
                preparedStatement.setString(index++, value);
            }
            preparedStatement.setString(index, map.get(primaryKey));

            //执行语句
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("sql语句错误");
        }
        return 0;
    }

    /**
     * 删除数据
     */
    public int remove() {
        //获取映射
        Map<String, String> map = analysisAnnotation();
        //拼接sql语句
        String sql = "delete from " + tableName + " where " + primaryKey + " = ? ";
        Connection conn = null;
        try {
            //连接数据库
            conn = DBUtil.getConn();
            //获取preparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, map.get(primaryKey));

            //执行语句
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("sql语句错误");
        }
        return 0;
    }

    /**
     * 查询全部数据
     */
    public List<Object> queryForList() {

        List<Object> result = new ArrayList<>();
        Map<String, String> map = analysisAnnotation();
        String sql = "select * from " + tableName;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtil.getConn();
            preparedStatement = conn.prepareStatement(sql);
            //执行查询语句 返回结果集
            resultSet = preparedStatement.executeQuery();
            //获取列数
            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                BaseModel instance = this.getClass().newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    //通过列名获取 属性的名字 进而获取字段名
                    Field field = instance
                            .getClass()
                            .getDeclaredField(colToField.get(resultSet.getMetaData().getColumnName(i)));
                    field.setAccessible(true);
                    //设置该实例字段的值
                    field.set(instance, resultSet.getObject(i));
                }
                //加入
                result.add(instance);
            }
            return result;


        } catch (Exception e) {
            log.error("查询失败");
            return result;

        } finally {
            DBUtil.close(conn, preparedStatement, resultSet);
        }

    }





}
