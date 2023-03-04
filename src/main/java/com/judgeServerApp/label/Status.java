package com.judgeServerApp.label;


/**
 * @author yang
 */
public class Status {

    /** 请求格式不正确 */
    public static final Integer FORMAT_ERROR = -2;

    /** 代码通过所有测试点 */
    public static final Integer ACCEPT = 0;

    /** 代码通过部分测试点 */
    public static final Integer PARTIALLY_ACCEPT = 10;

    /** 测试点全错 */
    public static final Integer ALL_WRONG = 1;

    /** 超时 */
    public static final Integer TIME_ERROR = -999;

    /** 请求状态正常 */
    public static final Integer OK = 200;

    /** 服务器出错 */
    public static final Integer SYSTEM_ERROR = 500;

    private Status(){}
}
