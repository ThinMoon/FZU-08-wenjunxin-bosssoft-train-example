package com.bosssoft.hr.train.Thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ThreadPoolTest {

    @Test
    public void testThreadPool() {
        ThreadPoolExecutor threadPool = ThreadPool.createThreadPool();
        for (int i = 0; i < 6; i++) {
            threadPool.execute(new Task("" + i));

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
