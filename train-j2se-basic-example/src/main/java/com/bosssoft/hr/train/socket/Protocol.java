package com.bosssoft.hr.train.socket;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 传输的数据协议定义这里 记得序列化否则无法放对象流传输
 * 虽然我们借助json传输不用到还是写下留一手吧
 * @author: Administrator
 * @create: 2020-05-28 22:17
 * @since
 **/

public class Protocol implements Serializable {
    /**
     * 这里是一个内部类 报文投 一般是包含
     */
    class Head{
        /**
         * 版本 业务流水号 等 公共字段 这里省略啦
         */

        /**
         * 定义body类型，方便服务端强制转化body
         */
        int bodyType;
    }
    Object body;
}

