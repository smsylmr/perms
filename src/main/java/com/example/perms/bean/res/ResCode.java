package com.example.perms.bean.res;

/**
 * @author lsl
 * @date 2020/01/26
 * @description 返回状态码以及信息
 */
public enum ResCode {
    //状态码定义分
    OK(2000,"请求成功！",true),
    LOGOUT_SUCCESS(2001,"用户退出成功",true),

    USERNAME_PASSWORD_ERROR(1001,"用户名或密码错误",false),
    USERNAME_NOT_EXIST(1002,"用户不存在",false),
    USERNAME_DISABLE(1003,"用户已禁用",false),
    CAPTCHA_NOT_CORRECT(1004,"验证码不正确",false),
    USER_LOGIN_FAILED(1005,"用户登录失败",false),
    USER_NOT_LOGIN(1006,"用户未登录或已过期，请先登录！",false),
    NO_ACCESS(4001,"没有访问权限",false),
    SYSTEM_ERROR(4002,"系统错误",false),
    URL_ERROR(4002,"找不到请求路径",false),
    SERVICE_OK(0,"返回成功！",true),

    //主链模块
    USER_NOT_EXIST_M(110101,"用户不存在！",false),
    USER_DISABLE_M(110102,"用户已禁用！",false),
    PASSWORD_ERROR_M(110103,"密码错误！",false),
    CAPTCHA_ERROR_M(110104,"验证码有误！",false),
    CAPTCHA_NULL_M(110105,"验证码为空！",false),
    CAPTCHA_NOT_EXIST_M(110106,"验证码不存在！",false),
    UNKNOWN_ERROR_M(110109,"未知异常！",false),

    LOGIN_SUCCESS_M(110200,"登录成功！",true),
    LOGOUT_SUCCESS_M(0,"退出成功！",true),
    NO_AUTH_SIGN_M(110401,"没有访问权限！",false),
    NO_ROLE_M(110401,"没有角色！",false),

    PARAM_NOT_ALLOWED_M(110501,"请求参数不合法，请检查！",false),
    DOWNLOAD_STREAM_NOT_EXIST(120001,"下载证书失败,没有生成的证书",false),

    TOKEN_ERROR(111111,"token不存在或已过期！",false),
    USER_LOGIN_REPEAT(111111,"用户已经在其他地方登录，请重新登录！",false),
    USER_DISABLED(111112,"用户已被禁用！",false),
    USER_OR_PASSWORD_NULL_M(111111,"用户名或密码不能为空！",false),

            ;




    private int code;
    private String message;
    private boolean success;//返回成功或失败，成功前端直接取值，失败前端取错误状态码

    private ResCode(int code, String message,boolean success){
        this.code = code;
        this.message = message;
        this.success = success;

    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
    
}
