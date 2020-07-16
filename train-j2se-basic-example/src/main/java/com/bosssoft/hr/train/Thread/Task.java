package com.bosssoft.hr.train.Thread;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author 温俊欣
 * 任务
 */
@Slf4j
public class Task implements Runnable {

    private final String name;

    public Task(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        log.info("star task:{}", name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        log.info("end task:{} " + name);
    }
}
