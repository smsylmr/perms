package com.example.perms.exception;

import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public Result handleValidException(RuntimeException ex) {
        logger.error("system error", ex);
        Result ret = new Result(ResCode.SYSTEM_ERROR);
        return ret;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNotFoundException(NoHandlerFoundException ex) {
        logger.error("请求路径错误");
        Result ret = new Result(ResCode.URL_ERROR);
        return ret;

    }

}
