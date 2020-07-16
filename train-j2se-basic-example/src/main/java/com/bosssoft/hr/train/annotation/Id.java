package com.bosssoft.hr.train.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 瘦明月
 * @description: 注解在字段上 对应数据表ID
 *
 */
@Target(ElementType.FIELD)//表示注解在字段上
@Retention(RetentionPolicy.RUNTIME)//表示该注解生命周期在运行期
public @interface Id {
    /**
     * 定义value默认为id
     * @return
     */
    String value() default "id";
}
