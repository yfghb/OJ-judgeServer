package com.judgeServerApp.common;

import lombok.Data;

import java.util.List;


/**
 * @author yang
 */
@Data
public class ServerResponse {
    /** 运行结果的状态 */
    private String status;

    /** 编译信息 */
    private String compileMsg;

    /** 测试点状态 */
    private List<String> tag;

    /** 测试点花费的时间 */
    private List<String> time;

    /** 测试点花费的内存 */
    private List<String> memory;

    /** 测试点运行错误的信息 */
    private List<String> errorMsg;

}
