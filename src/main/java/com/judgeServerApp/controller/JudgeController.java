package com.judgeServerApp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.judgeServerApp.common.ServerRequest;
import com.judgeServerApp.common.ServerResponse;
import com.judgeServerApp.common.TestCase;
import com.judgeServerApp.label.Status;
import com.judgeServerApp.label.ThreadPoolParam;
import com.judgeServerApp.threadTask.RunCodeThread;
import com.judgeServerApp.threadTask.ThreadFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.*;


import static com.judgeServerApp.controller.Utils.*;

/**
 * @author yang
 */
@RestController
@RequestMapping("/judgeServer")
public class JudgeController {

    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            ThreadPoolParam.CORE_POOL_SIZE,
            ThreadPoolParam.MAX_POOL_SIZE,
            ThreadPoolParam.KEEP_ALIVE_TIME,
            ThreadPoolParam.UNIT,
            new ArrayBlockingQueue<>(20),
            new ThreadFactory()
    );

    @PostMapping("/run")
    public JSONArray run(@RequestBody ServerRequest request){
        JSONArray jsonArray;
        List<TestCase> caseList = new ArrayList<>();
        List<RunCodeThread> threadList = new ArrayList<>();
        ServerResponse res = new ServerResponse();
        String uuid = UUID.randomUUID().toString();
        request.setUuid(uuid);
        // 检查请求的数据格式是否正确
        if(Objects.equals(checkRequest(request), Status.FORMAT_ERROR)){
            res.setStatus(Status.FORMAT_ERROR);
            String str = JSON.toJSONString(res);
            jsonArray = JSON.parseArray(str);
            return jsonArray;
        }
        try {
            // 将代码写入文件
            fileReady(request);
            if(request.getIsReadFile()){
                // 读取输入输出测试点并copy一份到request
                inOutReady(request);
            }
            // 设置编译/运行的命令
            setCmd(request);
            // 提交所有线程的任务，加速判题
            for(int i=0;i<request.getInputList().size();i++){
                TestCase testCase = new TestCase();
                RunCodeThread thread = new RunCodeThread(request, testCase,i);
                threadList.add(thread);
            }
            List<Future<TestCase>> futures = THREAD_POOL.invokeAll(threadList, request.getTimeLimit()+1, TimeUnit.SECONDS);
            // 收集结果
            for(Future<TestCase> future:futures){
                caseList.add(future.get());
            }
            res.setCaseList(caseList);
            // 设置结果的状态
            checkResult(res,request.getInputList().size());
        } catch (Exception e) {
            res.setStatus(Status.SYSTEM_ERROR);
            e.printStackTrace();
        }finally {
            String str = JSON.toJSONString(res);
            jsonArray = JSON.parseArray(str);
        }
        return jsonArray;
    }
}
