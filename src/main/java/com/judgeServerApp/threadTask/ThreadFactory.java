package com.judgeServerApp.threadTask;

/**
 * @author yang
 */
public class ThreadFactory implements java.util.concurrent.ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(String.valueOf(System.currentTimeMillis()));
        return thread;
    }

}
