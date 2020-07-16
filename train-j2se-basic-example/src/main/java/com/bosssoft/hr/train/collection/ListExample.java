package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;

/**
 * @author 温俊欣
 */
public interface ListExample<T> {
    /**
     * 加入尾部
     * @param user
     * @return
     */
    boolean  append(T user);

    /**
     * 返回指定位置
     * @param index
     * @return
     */
    User get(Integer index);

    /**
     * 中间删除
     * @param position
     * @return
     */
    boolean remove(Integer position);

    /**
     * 按下表遍历 注意这种方式遍历过程不可以删除
     */
    void listByIndex();

    /**
     * 按迭代器遍历
     */
    void listByIterator();

    /**
     * 列表数组转换
     * @return
     */
    User[] toArray();

    /**
     * 列表排序演示
     */
    void sort();



}
