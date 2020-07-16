package com.bosssoft.hr.train.jsp.example.service;

import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;
import com.bosssoft.hr.train.jsp.example.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
        User user =new User(1,"jim");
        assertTrue(userService.save(user));

        //测试类不能够按顺序执行 只能删除防止重复了
        assertTrue(userService.remove(1));

        //测试没有id 和 名字的参数
        User user2 =new User();
        assertTrue(userService.save(user2));

    }

    /**
     * 测试插入重复
     */
    @Test
    public void saveSameId() {
        User user =new User(1,"jim");
        assertTrue(userService.save(user));

        User user2 =new User(1,"jim");
        assertFalse(userService.save(user));

        //测试类不能够按顺序执行 只能删除防止重复了
        assertTrue(userService.remove(1));
    }


    /**
     * 依据界面可能开展的测试编写测试用例  例如某一些字段为空等，实际就是依据
     * 项目需求的测试用例来扩展测试用例
     */
    @Test
    public void update() {
        User user =new User(1,"jim");
        assertTrue(userService.save(user));
        user.setPassword("123456");
        user.setCode("sss");
        assertTrue(userService.update(user));
        //删除
        assertTrue(userService.remove(1));



    }


    /**
     * 测试移除用户
     */
    @Test
    public void remove() {
        User user =new User(1,"jim");
        assertTrue(userService.save(user));

        //移除数据库中不存在的id
        assertFalse(userService.remove(20));
        //移除存在的id
        assertTrue(userService.remove(1));


    }



    @Test
    public void queryByCondition() {

        User user =new User(1,"jim");
        user.setPassword("123456");
        assertTrue(userService.save(user));

        List<User> users = userService.queryByCondition(new Query("jim", "123456", false));
        for (User u : users) {
            log.info(u.toString());
        }

        //测试查询数据库中所有
        List<User> users2 = userService.queryByCondition(new Query("jim", "123456", true));
        for (User u : users2) {
            log.info(u.toString());
        }

        //移除存在的id
        assertTrue(userService.remove(1));

    }

    /**
     * 测试正确账号和错误账号
     */
    @Test
    public void authentication() {
        //测试正确
        User user =new User(1,"001");
        user.setPassword("123456");
        assertTrue(userService.save(user));

        Assert.assertEquals(true, userService.authentication(user));

        //测试错误账号
        User user2=new User("003","123456");
        Assert.assertEquals(false, userService.authentication(user2));
        //移除存在的id
        assertTrue(userService.remove(1));

    }




}
