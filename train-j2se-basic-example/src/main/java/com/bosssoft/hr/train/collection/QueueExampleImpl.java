package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 瘦明月
 * 队列方法实现类
 */
public class QueueExampleImpl implements QueueExample<User> {


    /**
     * LinkedList实现了队列接口
     * */
    Queue<User> users = new LinkedList<>();

    @Override
    public boolean add(User user) {
        return users.add(user);
    }

    @Override
    public boolean offer(User user) {
        return users.offer(user);
    }

    @Override
    public User remove() {
        return users.remove();
    }

    @Override
    public User poll() {
        return users.poll();
    }

    @Override
    public User element() {
        return users.element();
    }

    @Override
    public User peek() {
        return users.peek();
    }
}
