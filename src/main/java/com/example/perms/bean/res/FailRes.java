package com.example.perms.bean.res;

public enum FailRes {
    //状态码定义分
    USERNAME_PASSWORD_ERROR(1001,"用户名或密码错误"),
    USERNAME_NOT_EXIST(1002,"用户不存在"),
    USERNAME_DISABLE(1003,"用户已禁用"),
    CAPTCHA_NOT_CORRECT(1004,"验证码不正确"),
    TOKEN_ERROR(1005,"token不存在或已过期"),
    USER_LOGIN_REPEAT(1006,"用户已经在其他地方登录，请重新登录！"),

    NO_ACCESS(4001,"没有访问权限"),

    UNKNOWN_ERROR(5001,"未知异常！"),


    ;




    private int code;
    private String message;

    private FailRes(int code, String message){
        this.code = code;
        this.message = message;

    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
