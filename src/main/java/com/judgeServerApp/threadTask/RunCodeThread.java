package com.judgeServerApp.threadTask;

import com.judgeServerApp.common.ServerRequest;
import com.judgeServerApp.common.TestCase;
import com.judgeServerApp.run.RunCode;
import lombok.Data;

import java.util.concurrent.Callable;

/**
 * @author yang
 */
@Data
public class RunCodeThread implements Callable<TestCase> {
    private final RunCode runCode;
    /**
     * 构造函数
     * @param request 请求对象
     * @param testCase 测试点结果
     * @param index 测试点编号(索引)
     */
    public RunCodeThread(ServerRequest request, TestCase testCase, Integer index){
        this.runCode = new RunCode(request, testCase,index);
    }

    @Override
    public TestCase call() {
        runCode.compile();
        runCode.running();
        return runCode.getResponse();
    }
}
