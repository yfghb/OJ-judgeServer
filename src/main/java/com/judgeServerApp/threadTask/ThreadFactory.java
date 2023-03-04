package com.judgeServerApp.threadTask;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author yang
 */
@Slf4j
public class ThreadFactory implements java.util.concurrent.ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = UUID.randomUUID().toString();
        thread.setName(name);
        log.info("创建线程");
        return thread;
    }

}
