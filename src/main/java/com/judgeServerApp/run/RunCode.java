package com.judgeServerApp.run;

import com.judgeServerApp.common.ServerRequest;
import com.judgeServerApp.common.ServerResponse;
import com.judgeServerApp.label.Tag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author yang
 */
public class RunCode {
    private final String compileCmd;
    private final String RunCmd;
    private final Integer timeLimit;
    private final String input;
    private static final long ONE_SECOND = 1000;
    private final ServerResponse response;

    RunCode(ServerRequest request,ServerResponse response,Integer index){
        this.compileCmd = request.getCompileCmd();
        this.RunCmd = request.getRunCmd();
        this.timeLimit = request.getTimeLimit();
        this.input = request.getInputList().get(index);
        this.response = response;
    }

    public void compile(){
        try{
            Process compile = Runtime.getRuntime().exec(this.compileCmd);
            compile.waitFor();

            //获得编译的错误信息
            BufferedReader failReader = new BufferedReader(new InputStreamReader(compile.getErrorStream(), "gbk"));
            int flag = 0;
            String line;
            StringBuilder failMessage = new StringBuilder();
            while ((line = failReader.readLine()) != null){
                flag=1;
                failMessage.append(line).append('\n');
            }
            if(flag==1){
                compile.destroy();
                this.response.setCompileMsg(failMessage.toString());
                return;
            }
            compile.destroy();
        }catch (Exception e){
            this.response.setCompileMsg("compile error");
            this.response.setErrorMsg(e.getMessage());
            return;
        }
        this.response.setCompileMsg("compile accept");
    }

    public void running(){
        StringBuilder result = new StringBuilder();
        try{
            this.response.setInput(this.input);
            Process process = Runtime.getRuntime().exec(this.RunCmd);

            //输入到用户代码里（样例输入）
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(this.input);
            writer.close();

            //开始计时
            long startTime =  System.currentTimeMillis();

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(),"gbk"));
            //获得运行的错误信息
            String errorLine;
            int flag = 0;
            StringBuilder errorMessage = new StringBuilder();
            while ((errorLine = errorReader.readLine()) != null){
                flag=1;
                errorMessage.append(errorLine).append('\n');
            }
            errorReader.close();

            if (flag==1) {
                process.destroy();
                if(this.response.getErrorMsg()==null){
                    this.response.setTag(Tag.RUN_FAIL);
                    this.response.setErrorMsg(errorMessage.toString());
                    return;
                }
            }

            //TLE错误
            process.waitFor();
            long endTime = System.currentTimeMillis();
            long calcTime = endTime - startTime;
            this.response.setTime(calcTime + "ms");
            if(calcTime > this.timeLimit * ONE_SECOND){
                process.destroy();
                this.response.setTag(Tag.TIME_LIMIT_EXCEEDED);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            //获得执行结果，即执行文件System.out的内容
            while ((line = reader.readLine()) != null) {
                result.append(line).append('\n');
            }
            //去除多余的'\n'
            result.delete(result.length()-1,result.length());
            reader.close();
            process.destroy();
            this.response.setAnswer(result.toString());
        }catch (Exception e){
            this.response.setTag(Tag.RUN_FAIL);
            if(this.response.getErrorMsg()==null){
                this.response.setErrorMsg(e.getMessage());
            }
        }

    }
}
