package com.judgeServerApp.common;

import lombok.Data;



/**
 * @author yang
 */
@Data
public class ServerResponse {
    /** 运行结果的状态 */
    private Integer status;

    /** 编译信息 */
    private String compileMsg;

    /** 测试点输入 */
    private String input;

    /** 测试点输出 */
    private String output;

    /** 用户代码的输出 */
    private String answer;

    /** 测试点状态 */
    private Integer tag;

    /** 测试点花费的时间 */
    private String time;

    /** 测试点花费的内存 */
    private String memory;

    /** 测试点运行错误的信息 */
    private String errorMsg;

}
