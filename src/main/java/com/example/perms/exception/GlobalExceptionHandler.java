package com.example.perms.exception;

import com.alibaba.fastjson.JSON;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(AuthenticationException.class)
    public Result handleNotFoundException(AuthenticationException failed) {
        Result error = new Result(ResCode.USER_LOGIN_FAILED, failed);
        if (failed instanceof LockedException) {
            error.setMessage("账户被锁定，请联系管理员!");
        } else if (failed instanceof CredentialsExpiredException) {
            error.setMessage("密码过期，请联系管理员!");
        } else if (failed instanceof AccountExpiredException) {
            error.setMessage("账户过期，请联系管理员!");
        } else if (failed instanceof DisabledException) {
            error.setMessage("账户被禁用，请联系管理员!");
        } else if (failed instanceof BadCredentialsException) {
            error.setMessage("用户名或者密码输入错误，请重新输入!");
        }
        return error;
    }
}
