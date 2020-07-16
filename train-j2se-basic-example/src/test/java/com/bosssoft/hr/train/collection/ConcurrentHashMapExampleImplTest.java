package com.bosssoft.hr.train.collection;

import com.bosssoft.hr.train.pojo.Resource;
import com.bosssoft.hr.train.pojo.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConcurrentHashMapExampleImplTest {


    Role role1;
    Role role2;
    Role role3;

    Resource resource1;
    Resource resource2;
    Resource resource3;

    ConcurrentHashMapExampleImpl example;

    @Before
    public void setUp() throws Exception {
        role1 = new Role(1001, "role1");
        role2 = new Role(1002, "role2");
        role3 = new Role(1003, "role3");
        resource1 = new Resource(1001, "re1");
        resource2 = new Resource(1002, "re2");
        resource3 = new Resource(1003, "re3");

        example = new ConcurrentHashMapExampleImpl();
    }

    @After
    public void tearDown() throws Exception {
        role1 = null;
        role2 = null;
        role3 = null;
        resource1 = null;
        resource2 = null;
        resource3 = null;

        example = null;
    }

    @Test
    public void put() {
        assertEquals(null,example.put(role1, resource1));
        assertEquals(null,example.put(role2, resource2));
        assertEquals(resource2,example.put(role2, resource3));

    }

    @Test
    public void remove() {
        assertEquals(null,example.put(role1, resource1));
        assertEquals(null,example.put(role2, resource2));

        assertEquals(resource1, example.remove(role1));
    }

    @Test
    public void containsKey() {
        assertEquals(null,example.put(role1, resource1));
        assertEquals(null,example.put(role2, resource2));

        assertTrue(example.containsKey(role1));
        assertTrue(example.containsKey(role2));
    }

    @Test
    public void visitByEntryset() {
        assertEquals(null,example.put(role1, resource1));
        assertEquals(null,example.put(role2, resource2));
        assertEquals(null,example.put(role3, resource3));

        example.visitByEntryset();
    }

    @Test
    public void visitByKeyset() {

        assertEquals(null,example.put(role1, resource1));
        assertEquals(null,example.put(role2, resource2));
        assertEquals(null,example.put(role3, resource3));


        example.visitByKeyset();

    }

    @Test
    public void visitByValues() {
        assertEquals(null,example.put(role1, resource1));
        assertEquals(null,example.put(role2, resource2));
        assertEquals(null,example.put(role3, resource3));

        example.visitByValues();

    }




}
