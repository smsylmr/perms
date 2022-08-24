package com.example.perms.bean.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final Long serialVersionUID = 1L;

    //@ApiModelProperty("返回状态码")
    private int code;

    //@ApiModelProperty("文字描述")
    private String message;

    //@ApiModelProperty("需要返回的数据")
    private T data;

    //@ApiModelProperty("返回当前时间戳")
    private long timestamp;

    //@ApiModelProperty("返回成功或失败的状态")
    private boolean success;

    /**
     *
     * @param message 成功或失败的信息
     * @param success 成功或失败的布尔值
     * @param code 成功或失败状态码
     */
    public Result(String message,boolean success,int code){
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.success = success;
    }

    public Result(T data){
        this.code = 2000;
        this.message = "";
        this.timestamp = System.currentTimeMillis();
        this.success = true;
        this.data=data;
    }


    public Result(String message){
        this.code = 110011;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.success = false;
    }
    public Result(int code, String message){
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.success = false;
    }

    public Result(ResCode ResCode, T data){
        this.code = ResCode.getCode();
        this.message = ResCode.getMessage();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
        this.success = ResCode.isSuccess();
    }
    public Result(ResCode ResCode, Exception e){
        this.code = ResCode.getCode();
        this.message = e.getMessage();
        this.timestamp = System.currentTimeMillis();
        this.success = ResCode.isSuccess();
    }

    public Result(ResCode ResCode){
        this.code = ResCode.getCode();
        this.message = ResCode.getMessage();
        this.timestamp = System.currentTimeMillis();
        this.success = ResCode.isSuccess();
    }

    public Result(FailRes res){
        this.success = false;
        this.code = res.getCode();
        this.message = res.getMessage();
        this.timestamp = System.currentTimeMillis();
    }
}
