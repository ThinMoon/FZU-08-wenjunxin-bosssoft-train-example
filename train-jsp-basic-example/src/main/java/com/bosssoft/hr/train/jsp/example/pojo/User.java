package com.bosssoft.hr.train.jsp.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @param
 * @description: 判断对象相等重写 equals 和 hashcode 很重要哦
 *           加上@Data 注解不再需要编写
 * @author: Administrator
 * @create: 2020-05-22 14:52
 * @since
 **/
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private Integer id;
    private String name;

    private String code;
    private String password;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}


