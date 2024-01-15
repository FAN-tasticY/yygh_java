package com.atguigu.yygh.common.handler;


import com.atguigu.yygh.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice       // 凡是@restcontrolleradvice进行处理的类，都是全局异常处理类
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class) //表示的是异常处理的粒度
    public R handlerException(Exception ex){
        ex.printStackTrace();   // 控制台输出错误信息
        log.error(ex.getMessage());
        return R.error().message(ex.getMessage());
    }

    @ExceptionHandler(value = SQLException.class)   // sql异常
    public R handlerSqlException(SQLException sql){
        sql.printStackTrace();
        log.error(sql.getMessage());
        return R.error().message("sql异常");
    }
}
