package com.judgeServerApp.controller;

import com.alibaba.fastjson.JSONArray;
import com.judgeServerApp.common.ServerRequest;
import com.judgeServerApp.common.ServerResponse;
import com.judgeServerApp.label.Status;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.judgeServerApp.controller.Utils.*;

/**
 * @author yang
 */
@RestController
@RequestMapping("/judgeServer")
public class JudgeController {

    @PostMapping("/run")
    public JSONArray run(@RequestBody ServerRequest request){
        ServerResponse response = new ServerResponse();
        // 检查请求的数据格式是否正确
        if(Objects.equals(checkRequest(request), Status.FORMAT_ERROR)){
            response.setStatus(Status.FORMAT_ERROR);
        }
        // 将代码写入文件
        String filename = writeInFile(request);
        // 设置请求的uuid等属性
        String uuid = setAttribute(request,filename);
        return null;
    }
}
