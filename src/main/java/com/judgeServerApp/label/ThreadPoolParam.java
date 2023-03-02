package com.judgeServerApp.label;

import java.util.concurrent.TimeUnit;

/**
 * @author yang
 */
public class ThreadPoolParam {
    /** 线程池核心线程池大小 */
    public static final int CORE_POOL_SIZE = 20;

    /** 线程池最大线程数量 */
    public static final int MAX_POOL_SIZE = 25;

    /** 空闲线程存活时间 */
    public static final long KEEP_ALIVE_TIME = 3;

    /** 空闲线程存活时间单位 */
    public static final TimeUnit UNIT = TimeUnit.SECONDS;

}
