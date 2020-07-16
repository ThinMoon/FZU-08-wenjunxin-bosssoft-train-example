package com.bosssoft.hr.train.jsp.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 瘦明月
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Query {

    /**
     * 根据用户名密码进行查询
     */
    String name;
    String password;

    /**
     * 查询数据库中所有用户信息标志位
     */
    Boolean isAll;

}
