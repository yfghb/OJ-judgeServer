package com.judgeServerApp.threadTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yang
 */
@Slf4j
public class ThreadFactory implements java.util.concurrent.ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = String.valueOf(System.currentTimeMillis());
        thread.setName(name);
        log.info("创建线程:"+name);
        return thread;
    }

}
