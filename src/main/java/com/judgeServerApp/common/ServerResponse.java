package com.judgeServerApp.common;

import lombok.Data;

import java.util.List;

/**
 * @author yang
 */
@Data
public class ServerResponse {
    private Integer status;
    private List<TestCase> caseList;
}
