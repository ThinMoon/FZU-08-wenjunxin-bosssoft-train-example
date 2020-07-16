package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class QueueExampleImplTest {

    User user1;
    User user2;

    QueueExampleImpl queueExample;

    @Before
    public void setup() {
        user1 = new User();
        user2 = new User();

        user1.setId(1001);
        user1.setName("ZhangSan");
        user1.setId(1002);
        user1.setName("LiSi");

        queueExample = new QueueExampleImpl();

    }

    @After
    public void teardown() {
        user1 = null;
        user2 = null;
        queueExample = null;
    }

    @Test
    public void add() {
        assertTrue(queueExample.add(user1));
    }

    @Test
    public void offer() {
        assertTrue(queueExample.offer(user1));
    }

    @Test
    public void remove() {
        assertTrue(queueExample.add(user1));
        assertEquals(user1,queueExample.remove());

        try {
            queueExample.remove();
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void poll() {
        assertTrue(queueExample.add(user1));
        assertEquals(user1,queueExample.poll());
        assertNull(queueExample.poll());
    }

    @Test
    public void element() {

        assertTrue(queueExample.add(user1));
        assertEquals(user1,queueExample.element());
        assertEquals(user1,queueExample.element());
    }

    @Test
    public void pick() {

        assertTrue(queueExample.add(user1));
        assertEquals(user1,queueExample.peek());
        assertEquals(user1,queueExample.peek());

    }



}
