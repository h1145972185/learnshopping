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
    NOT_AUTHORITY(13,"没有权限"),
    NEED_LOGIN(14,"用户不能为空"),
    ON_SALE(15,"在售"),
    LOWER_SHELF(16,"下架"),
    PRODUCT_DELETE(17,"删除"),
    LIMIT_NUM_SUCCESS(1,"LIMIT_NUM_SUCCESS"),
    LIMIT_NUM_FAIL(0,"LIMIT_NUM_FAIL "),
    UPDATE_FAIL(18,"更新失败"),
    PRODUCT_CHECKED(1,"已勾选"),
    PRODUCT_UNCHECKED(0,"未勾选"),
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

    public enum CartCheckedEnum{
        PRODUCT_CHECKED(1,"已勾选"),
        PRODUCT_UNCHECKED(0,"未勾选"),

        ;
        private int code;
        private  String desc;
        CartCheckedEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum PaymentWayEnum{
        //       1-支付宝   2-微信
        ALIPAY(1,"支付宝"),
        WECHAT(2,"微信")

        ;
        private Integer code;
        private String desc;

        PaymentWayEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static PaymentWayEnum codeOf(Integer code){
            for (PaymentWayEnum paymentWayEnum:
                    values()) {
                if(code == paymentWayEnum.getCode()){
                    return paymentWayEnum;
                }
            }
            return null;
        }
    }
}
