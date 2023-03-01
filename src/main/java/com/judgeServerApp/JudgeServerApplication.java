package com.judgeServerApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yang
 */
@SpringBootApplication
@Slf4j
public class JudgeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudgeServerApplication.class, args);
        // 创建SecurityManager实例
        SecurityManager securityManager = new SecurityManager();
        // 设置启动
        System.setSecurityManager(securityManager);
        log.info("项目启动成功！");
    }
}
