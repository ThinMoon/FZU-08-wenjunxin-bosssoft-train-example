package com.bosssoft.hr.train.pojo;

import lombok.Data;

/**
 * @description: 资源类在RBAC中可以理解是 菜单 按钮 或者 其他操作权限
 * @author: Administrator
 * @create: 2020-05-28 20:55
 * @since
 **/
@Data
public class Resource {
    private  Integer id;
    private String name;

    public Resource() {
    }

    public Resource(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

