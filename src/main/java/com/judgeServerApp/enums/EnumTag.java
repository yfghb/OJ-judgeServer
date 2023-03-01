package com.judgeServerApp.enums;

/**
 * @author yang
 */
public enum EnumTag {
    /** 测试点编译失败 */
    COMPILE_FAIL,

    /** 测试点运行失败 */
    RUN_FAIL,

    /** 测试点不正确 */
    WRONG_ANSWER,

    /** 测试点正确 */
    PASSED,

    /** 测试点运行超出时间限制 */
    TIME_LIMIT_EXCEEDED,

    /** 测试点运行超出内存限制 */
    MEMORY_LIMIT_EXCEEDED,

}
