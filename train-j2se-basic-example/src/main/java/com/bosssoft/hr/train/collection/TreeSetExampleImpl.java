package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author 温俊欣
 */
public class TreeSetExampleImpl implements TreeSetExample<User> {

    /**
     * 添加的元素必须正确实现Comparable接口，
     * 如果没有实现Comparable接口，
     * 那么创建TreeSet时必须传入一个Comparator对象
    **/
    TreeSet<User> users = new TreeSet<>(new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getId() - o2.getId();
        }
    });

    @Override
    public boolean add(User o) {
        return users.add(o);
    }

    @Override
    public boolean remove(User o) {
        return users.remove(o);
    }

    @Override
    public boolean contains(User o) {
        return users.contains(o);
    }

}
