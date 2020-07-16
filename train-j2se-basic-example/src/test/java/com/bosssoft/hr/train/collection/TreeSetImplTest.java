package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeSetImplTest {

    User user1;
    User user2;

    TreeSetExample treeSetExample;

    @Before
    public void setup() {
        user1 = new User();
        user2 = new User();

        user1.setId(1001);
        user1.setName("ZhangSan");
        user1.setId(1002);
        user1.setName("LiSi");

        treeSetExample = new TreeSetExampleImpl();

    }

    @After
    public void teardown() {
        user1 = null;
        user2 = null;
        treeSetExample = null;
    }

    @Test
    public void add() {
        assertTrue(treeSetExample.add(user1));
    }

    @Test
    public void remove() {
        assertTrue(treeSetExample.add(user1));
        assertTrue(treeSetExample.remove(user1));
    }

    @Test
    public void contains() {
        assertTrue(treeSetExample.add(user1));
        assertTrue(treeSetExample.contains(user1));
    }

}
