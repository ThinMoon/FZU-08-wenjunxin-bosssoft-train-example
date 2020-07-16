package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author 温俊欣
 */
@Slf4j
public class ArrayListExampleImpl implements ArrayListExample<User> {

    /**
     * 该对象用于演示ArrayList的主要方法
     */
    private List<User> users = new ArrayList<>();

    @Override
    public boolean insert(Integer position, User node) {

        if(position >= 0 && position < users.size()) {
            //add的重载方法，表示插入到指定位置
            users.add(position, node);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean append(User user) {
        return users.add(user);
    }

    @Override
    public User get(Integer index) {
        //保证index合法性
        return (index >=0 && index < users.size()) ? users.get(index) : null;
    }

    @Override
    public boolean remove(Integer position) {
        return (position >= 0 && position < users.size()) && users.remove(position.intValue()) != null;
    }

    @Override
    public void listByIndex() {
        int size = users.size();
        for (int i = 0; i < size; i++) {
            log.info(Constraint.LOG_TAG , users.get(i));
            /* 注意 如果在for遍历过程中作删除操作将导致异常 元素删除前移导致*/
            /** 以下代码被禁止 容易导致空指针？
             if(users.get(i).getName().equalsIgnoreCase("jim")){
             users.remove(i);
             }
             */
        }
    }

    @Override
    public void listByIterator() {
        Iterator<User> iterator = users.iterator();
        User user = null;
        while (iterator.hasNext()) {
            user = iterator.next();
            log.info(Constraint.LOG_TAG, user);
        }
    }

    @Override
    public User[] toArray() {
        User[] user = new User[users.size()];
        return this.users.toArray(user);

    }

    @Override
    public void sort() {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getId() - o2.getId();
            }
        });
    }

    public void sort2() {
        users.sort((o1, o2) -> o1.getId() - o2.getId());
    }
}
