package com.bosssoft.hr.train.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Map.*;

import static org.junit.Assert.*;


@Slf4j
public class UserModelTest {


    UserModel userModel;

    @Before
    public void setup() {
        userModel = new UserModel();
    }

    @After
    public void teardown() {
        userModel = null;
    }



    @Test
    public void analysisAnnotation() {
        Map<String, String> map = userModel.analysisAnnotation();

        assertNotNull(map);

        for (Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

    }

    @Test
    public void save() {
        assertEquals(1,userModel.save());
        assertEquals(1,userModel.remove());
    }

    @Test
    public void update() {

        assertEquals(1,userModel.save());
        userModel.setName("Tim");
        assertEquals(1,userModel.update());
        assertEquals(1,userModel.remove());
    }

    @Test
    public void remove() {
        assertEquals(1,userModel.save());
        assertEquals(1,userModel.remove());
    }

    @Test
    public void queryForList() {
        assertEquals(1,userModel.save());

        List<Object> objects = userModel.queryForList();
        for (Object obj: objects) {
            System.out.println(obj);
        }
        assertEquals(1,userModel.remove());

    }

}
