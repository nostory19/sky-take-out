package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        log.error("异常信息：{}", sqlIntegrityConstraintViolationException.getMessage());
//        return Result.error(sqlIntegrityConstraintViolationException.getMessage());
        String message = sqlIntegrityConstraintViolationException.getMessage();
        if (message.contains("Duplicate entry")){
            // 提示用户重复输入账号了
            // 要提取出错误信息中的账号名称
            String[] s = message.split(" ");
            String username = s[2];
//            String msg = username + "用户已存在";
            String msg = username + MessageConstant.ALEADY_EXIST;
            return Result.error(msg);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
}
