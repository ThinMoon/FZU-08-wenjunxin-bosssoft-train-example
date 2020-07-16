package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@Slf4j
public class LinkedListExampleImplTest {
    private LinkedListExampleImpl example;
    private User user1;
    private User user2;
    private User user3;


    @Before
    public void setUp() throws Exception {
        example = new LinkedListExampleImpl();
        user1 = new User();
        user2 = new User();
        user3 = new User();
        user1.setId(1001);
        user1.setName("ZhangSan");
        user2.setId(1002);
        user2.setName("LiSi");
        user3.setId(1003);
        user3.setName("WangWu");

    }

    @After
    public void tearDown() throws Exception {
        user1 = null;
        user2 = null;
        user3 = null;
        example = null;
    }

    @Test
    public void append() {
        //正常添加
        assertTrue(example.append(user1));
        assertTrue(example.append(user2));
        //添加null
        assertTrue(example.append(null));
    }

    @Test
    public void get() {
        //首先插入数据
        assertTrue(example.append(user1));
        assertTrue(example.append(user2));
        //测试正常范围
        assertEquals(user1, example.get(0));
        assertEquals(user2, example.get(1));
        //测试超出范围
        assertNull(example.get(-1));
        assertNull(example.get(2));

    }

    @Test
    public void remove() {

        //首先插入数据
        assertTrue(example.append(user1));
        assertTrue(example.append(user2));
        assertTrue(example.append(user3));

        //测试正常范围
        assertTrue(example.remove(0));
        //测试超出范围
        assertFalse(example.remove(-1));
        assertFalse(example.remove(3));


    }

    @Test
    public void listByIndex() {
        //首先插入数据
        assertTrue(example.append(user1));
        assertTrue(example.append(user2));
        assertTrue(example.append(user3));

        try {
            example.listByIndex();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void listByIterator() {
        //首先插入数据
        assertTrue(example.append(user1));
        assertTrue(example.append(user2));
        assertTrue(example.append(user3));

        try {
            example.listByIterator();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void toArray() {
        //首先插入数据
        assertTrue(example.append(user1));
        assertTrue(example.append(user2));
        assertTrue(example.append(user3));

        User[] users = new User[] {user1, user2, user3};

        assertEquals(users, example.toArray());
    }

    @Test
    public void sort() {
        //首先插入数据
        assertTrue(example.append(user1));
        assertTrue(example.append(user3));
        assertTrue(example.append(user2));

        example.sort();

        //查看排序后是否正确
        assertEquals(user1, example.get(0));
        assertEquals(user2, example.get(1));
        assertEquals(user3, example.get(2));


    }

    @Test
    public void sort2() {
        //首先插入数据
        assertTrue(example.append(user1));
        assertTrue(example.append(user3));
        assertTrue(example.append(user2));

        example.sort2();

        //查看排序后是否正确
        assertEquals(user1, example.get(0));
        assertEquals(user2, example.get(1));
        assertEquals(user3, example.get(2));
    }

    @Test
    public void addFirst() {
        example.addFirst(user1);
        assertEquals(user1, example.get(0));
        example.addFirst(user2);
        assertEquals(user2, example.get(0));

    }

    @Test
    public void offer() {

        example.offer(user1);
        assertEquals(user1, example.get(0));
        example.offer(user2);
        assertEquals(user2, example.get(1));
        example.offer(user3);
        assertEquals(user3, example.get(2));


    }

    @Test
    public void sychronizedVisit() {
        example.sychronizedVisit(user1);
        assertEquals(user1, example.get(0));
    }

    @Test
    public void push() {
        example.push(user1);
        example.push(user2);
        assertEquals(user2, example.pop());
        assertEquals(user1, example.pop());
    }

    @Test
    public void pop() {
        example.push(user1);
        example.push(user2);
        assertEquals(user2, example.pop());
        assertEquals(user1, example.pop());
    }

}
