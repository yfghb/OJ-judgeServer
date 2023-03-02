package com.judgeServerApp.controller;


import com.alibaba.fastjson.JSON;
import com.judgeServerApp.common.InPoints;
import com.judgeServerApp.common.OutPoints;
import com.judgeServerApp.common.ServerRequest;
import com.judgeServerApp.common.TestCase;
import com.judgeServerApp.label.Status;
import com.judgeServerApp.label.SystemEnv;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.io.*;
import java.util.Objects;


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
            request.getFileSuffix()==null ||
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

    public static void setCmd(ServerRequest request, String file){

    }

    public static String fileReady(ServerRequest request) throws IOException {
        String filename = request.getUuid();
        String suffix = request.getFileSuffix();
        String code = request.getCode();
        String java = "java";
        if(Objects.equals(request.getLanguage(), java)){
            code = code.replaceAll("public class [a-zA-Z]+","public class "+filename);
        }
        String file = filename + suffix;
        String path = Objects.equals(request.getSystemEnv(), SystemEnv.WINDOWS)? SystemEnv.WINDOWS : SystemEnv.LINUX;
        File fp = new File(path + file);
        if(!fp.exists()){
            if(fp.createNewFile()){
                FileWriter writer = new FileWriter(path+file);
                // 清空原文件的内容
                writer.write("");
                // 将代码写入文件
                writer.write(code);
                writer.flush();
                writer.close();
            }else{
                throw new FileUploadException();
            }
        }

        return file;
    }

    public static void inOutReady(ServerRequest request){
        String inputPath = request.getInputFilePath();
        String outputPath = request.getOutputFilePath();
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        try{
            FileInputStream readInput = new FileInputStream(inputPath);
            FileInputStream readOutput = new FileInputStream(outputPath);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = readInput.read(bytes)) != -1){
                input.append(new String(bytes,0,len));
            }
            while ((len = readOutput.read(bytes)) != -1){
                output.append(new String(bytes,0,len));
            }
            InPoints inPoints = JSON.parseObject(input.toString(),InPoints.class);
            OutPoints outPoints = JSON.parseObject(output.toString(),OutPoints.class);
            request.setInputList(inPoints.getInput());
            request.setOutputList(outPoints.getOutput());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
