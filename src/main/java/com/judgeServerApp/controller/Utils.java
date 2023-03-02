package com.judgeServerApp.controller;

import com.judgeServerApp.cmd.JavaCmd;
import com.judgeServerApp.common.ServerRequest;
import com.judgeServerApp.label.Status;
import com.judgeServerApp.label.SystemEnv;
import java.util.UUID;

/**
 * @author yang
 */
public class Utils {
    public static Integer checkRequest(ServerRequest request){
        if(request==null){
            return Status.FORMAT_ERROR;
        }
        // 检查基本属性
        if(request.getLanguage()==null ||
            request.getCode()==null ||
            request.getIsReadFile()==null ||
            request.getTimeLimit()==null ||
            request.getPointCnt()==null ||
            request.getMemoryLimit()==null){
            return Status.FORMAT_ERROR;
        }
        // 检查是否以读取文件的方式获得输入输出测试点
        if(request.getIsReadFile()){
            if(request.getInputFilePath()==null || request.getOutputFilePath()==null){
                return Status.FORMAT_ERROR;
            }
        }else{
            if(request.getInputList()==null || request.getOutputList()==null){
                return Status.FORMAT_ERROR;
            }
        }
        // 检查命令是否为空
        if(request.getCompileCmd()==null || request.getRunCmd()==null){
            if(request.getSystemEnv()==null){
                return Status.FORMAT_ERROR;
            }
        }


        return Status.OK;
    }

    public static String setAttribute(ServerRequest request,String filename){
        String uuid = UUID.randomUUID().toString();
        String env = request.getSystemEnv();
        request.setUuid(uuid);
        if(env!=null){
            if(env.equals(SystemEnv.WINDOWS)){
                switch (request.getLanguage()){
                    case "java":
                        request.setCompileCmd(JavaCmd.windowsCompile(filename));
                        request.setRunCmd(JavaCmd.windowsRunning(filename));
                        break;
                    case "c":
                        System.out.println("c");
                        break;
                    case "python":
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + request.getLanguage());
                }
            }else if(env.equals(SystemEnv.LINUX)){
                switch (request.getLanguage()){
                    case "java":
                        request.setCompileCmd(JavaCmd.linuxCompile(filename));
                        request.setRunCmd(JavaCmd.linuxRunning(filename));
                        break;
                    case "c":
                        System.out.println("c");
                        break;
                    case "python":
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + request.getLanguage());
                }
            }
        }
        return uuid;
    }

    public static String writeInFile(ServerRequest request){
        return null;
    }
}
