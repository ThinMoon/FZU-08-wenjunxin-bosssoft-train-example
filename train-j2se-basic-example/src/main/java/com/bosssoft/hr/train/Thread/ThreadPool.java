package com.bosssoft.hr.train.Thread;

import java.util.concurrent.*;

/**
 * 使用Executors创建线程池可能会导致OOM(OutOfMemory ,内存溢出)
 * 这边使用ThreadPoolExecutor的构造函数来自己创建线程池
 * @author 瘦明月
 */
public class ThreadPool {

    public static ThreadPoolExecutor createThreadPool() {
        // 指定核心线程数量、最多线程数量、存活时间、阻塞队列、拒绝策略
        return new ThreadPoolExecutor(5,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardPolicy());

    }



}
