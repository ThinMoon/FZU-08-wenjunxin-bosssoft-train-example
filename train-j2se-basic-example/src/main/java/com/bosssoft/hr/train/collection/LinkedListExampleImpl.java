package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author 瘦明月
 * linkedlist测试实现类
 */
@Slf4j
public class LinkedListExampleImpl implements LinkedListExample<User> {

    /**
     * 该对象用于演示LinkedList的主要方法
     */
    private LinkedList<User> users = new LinkedList<>();


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

    /*===========LinkedList专有测试方法实现===========*/

    @Override
    public void addFirst(User node) {
        users.addFirst(node);
    }

    @Override
    public boolean offer(User node) {
        return users.offer(node);
    }

    @Override
    public void sychronizedVisit(User node) {
        /*这种方式可实现线程安全*/
        Collections.synchronizedCollection(users).add(node);
    }

    @Override
    public void push(User node) {
        users.push(node);
    }

    @Override
    public User pop() {
        return users.pop();
    }
}
