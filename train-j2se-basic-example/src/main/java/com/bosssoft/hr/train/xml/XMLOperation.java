package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;

/**
 * @author 温俊欣
 * 定义xml操作接口
 */
public interface XMLOperation <T extends Student> {

    boolean create(T object);
    boolean remove(T object);
    boolean update(T object);
    T query(T object);

}
