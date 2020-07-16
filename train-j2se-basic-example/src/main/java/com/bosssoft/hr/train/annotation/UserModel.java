package com.bosssoft.hr.train.annotation;


import lombok.Data;

/**
 * @author 瘦明月
 */
@Data
@Table("tt_user")
public class UserModel extends BaseModel {

    @Column("name")
    private String name = "LaoWang";

    @Id("id")
    private Integer id = 2;

    @Column("age")
    private Integer age = 333;

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
