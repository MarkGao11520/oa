package com.zrkj.oa.core;

import com.alibaba.fastjson.JSON;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static String genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE).toString();
    }

    public static String genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data).toString();
    }

    public static String genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message).toString();
    }
}
