package com.judgeServerApp.enums;

/**
 * @author yang
 */

public enum EnumStatus {
    /** 编译失败 */
    COMPILE_FAIL,

    /** 编译成功 */
    COMPILE_SUCCESS,

    /** 运行成功 */
    RUN_SUCCESS,

    /** 运行失败 */
    RUN_FAIL,

    /** 代码通过全部测试点 */
    ACCEPT,

    /** 代码全不通过测试点 */
    WRONG_ANSWER,

    /** 代码通过部分测试点 */
    PARTIALLY_ACCEPT,

    /** 运行超出时间限制 */
    TIME_LIMIT_EXCEEDED,

    /** 运行超出内存限制 */
    MEMORY_LIMIT_EXCEEDED,


}
