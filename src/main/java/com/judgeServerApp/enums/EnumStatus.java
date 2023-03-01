package com.judgeServerApp.enums;

/**
 * @author yang
 */
public enum EnumStatus {
    /** 请求格式不正确 */
    FORMAT_ERROR,

    /** 代码运行异常 */
    RUN_ERROR,

    /** 代码通过所有测试点 */
    ACCEPT,

    /** 代码通过部分测试点 */
    PARTIALLY_ACCEPT,

    /** 测试点全错 */
    ALL_WRONG,

    /** 服务超时 */
    TIME_ERROR,

}
