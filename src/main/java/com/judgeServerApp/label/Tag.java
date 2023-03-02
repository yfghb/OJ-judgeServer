package com.judgeServerApp.label;


/**
 * @author yang
 */
public class Tag {
    /** 测试点编译失败 */
    public static final Integer COMPILE_FAIL = -100;

    /** 测试点运行失败 */
    public static final Integer RUN_FAIL = -200;

    /** 测试点不正确 */
    public static final Integer WRONG_ANSWER = -300;

    /** 测试点正确 */
    public static final Integer PASSED = 20;

    /** 测试点运行超出时间限制 */
    public static final Integer TIME_LIMIT_EXCEEDED = -400;

    /** 测试点运行超出内存限制 */
    public static final Integer MEMORY_LIMIT_EXCEEDED = -500;

    private Tag(){}
}
