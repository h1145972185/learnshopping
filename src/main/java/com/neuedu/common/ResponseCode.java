package com.neuedu.common;

import sun.security.util.Password;

import java.awt.font.TextHitInfo;

public enum  ResponseCode {

    PARAM_EMPTY(2,"参数为空"),
    EXISTS_USERNAME(3,"用户名已经存在"),
    EXISTS_EMAIL(4,"邮箱已经存在"),
    NOT_EXISTS_USERNAME(3,"用户名不存在"),
    USER_NOT_LOGIN(6,"用户未登录"),
    NOQUESTION(7,"密保问题为空"),
    ANSWER_NULL(8,"答案为空"),
    ANSWER_ERROR(9,"答案错误"),
    PASSWORD_ERROR(10,"修改失败"),
    EXISTS_TOKEN(11,"token不存在"),
    TOKEN_ERROR(12,"token不一致"),
    NOT_AUTHORITY(13,"没有权限")

    ;
    private int status;
    private  String msg;


    ResponseCode(){
    }
    ResponseCode(int status,String msg){
        this.status=status;
        this.msg=msg;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
