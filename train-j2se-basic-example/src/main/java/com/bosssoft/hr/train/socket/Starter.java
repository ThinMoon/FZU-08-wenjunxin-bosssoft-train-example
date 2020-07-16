package com.bosssoft.hr.train.socket;


/**
 * @description: 负责socket 初始化工作
 * @author: Administrator
 * @create: 2020-05-28 22:32
 * @since
 **/
public interface Starter {
    /**
     * socket 初始化工作 ip指定 端口分配 等
     * @return
     */
    boolean start();
}

