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
        log.info("项目启动成功！");
    }
}
