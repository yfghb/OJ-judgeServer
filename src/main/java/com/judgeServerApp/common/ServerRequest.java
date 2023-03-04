package com.judgeServerApp.common;

import lombok.Data;

import java.util.List;

/**
 * @author yang
 */
@Data
public class ServerRequest {
    /** 唯一uuid */
    private String uuid;

    /** 运行环境 */
    private String systemEnv;

    /** 代码的语言 */
    private String language;

    /** 代码字符串 */
    private String code;

    /** 编译命令 */
    private String[] compileCmd;

    /** 运行命令 */
    private String[] runCmd;

    /** 文件后缀名 */
    private String fileSuffix;

    /** 时间限制 */
    private Integer timeLimit;

    /** 内存限制 */
    private String memoryLimit;

    /** 是否要读取输入输出文件 */
    private Boolean isReadFile;

    /** 输入测试点 */
    private List<String> inputList;

    /** 输出测试点 */
    private List<String> outputList;

    /** 输入测试点文件路径 */
    private String inputFilePath;

    /** 输出测试点文件路径 */
    private String outputFilePath;
}
