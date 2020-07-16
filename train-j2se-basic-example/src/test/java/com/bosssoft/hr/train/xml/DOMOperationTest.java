package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DOMOperationTest {

    Student student;
    DOMOperation domOperation;

    @Before
    public void setup() {
        student = new Student();
        student.setName("zhangSan");
        student.setAge(18);
        student.setId(1001);
        domOperation = new DOMOperation();
    }

    @After
    public void teardown() {

    }

    @Test
    public void create() {
        assertTrue(domOperation.create(student));
    }

    @Test
    public void remove() {
        assertTrue(domOperation.remove(student));
    }

    @Test
    public void update() {
        student.setAge(22);
        student.setName("LiHua");
        assertTrue(domOperation.update(student));
    }

    @Test
    public void query() {
        Student student = new Student();
        student.setId(1001);
        assertEquals("LiHua",domOperation.query(student).getName());
    }


}
