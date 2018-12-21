package com.neuedu.common;

public class Const {
    public static final Integer SUCCESS_CODE = 0;
    public static final Integer SUCCESS_ERROR = 1;

    public static final Integer USER_ROLE_CUSTOMER = 0;//普通用户
    public static final Integer USER_ROLE_ADMIN = 1;//管理员


    //判断是否登录时 将账户信息用session存储时的 setAttribute的k值
    public static final String CURRENTUSER = "CURRENTUSER";

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String UPLOAD_PATH = "D:\\uploadpic\\";

    public enum ResponseCodeEnum {
        NEED_LOGIN(2, "需要登录"),
        NO_PRIVILEGE(3, "无权限操作");

        private int code;
        private String desc;

        private ResponseCodeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
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

    public enum RoleEnum {
        ROLE_ADMIN(1, "管理员"),
        ROLE_CUSTPMER(0, "普通用户");

        private int code;
        private String desc;

        private RoleEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
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

    public enum ProductStatusEnum {

        PRODUCT_ONLINE(1, "在售"),
        PRODUCT_OFFLINE(2, "下架"),
        PRODUCT_DELETE(3, "删除");
        private int code;
        private String desc;

        private ProductStatusEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
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

    public enum OrderStatusEnum {
        //0-已取消  10-未付款  20-已付款 40-已发货 50-交易成功 60-交易关闭

        ORDER_CANCELED(0, "已取消"),
        ORDER_UN_PAY(10, "未付款"),
        ORDER_PAYED(20, "已付款"),
        ORDER_SEND(40, "已发货"),
        ORDER_SUCCESS(50, "交易成功"),
        ORDER_CLOSED(60, "交易关闭");

        private int code;
        private String desc;

        private OrderStatusEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;

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

        public static OrderStatusEnum codeOf(Integer code) {
            for (OrderStatusEnum orderStatusEnum:values()) {
                if (code == orderStatusEnum.getCode()) {
                    return orderStatusEnum;
                }
            }
            return null;
        }
    }



    public enum PaymentEnum {
        ONLINE(1, "线上支付"),;

        private int code;
        private String desc;

        private PaymentEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
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

        public static PaymentEnum codeOf(Integer code) {
            for (PaymentEnum paymentEnum : values()) {
                if (code == paymentEnum.getCode()) {
                    return paymentEnum;
                }
            }
            return null;
        }
    }

    public static final String FAIL = "failed";
    public static final String SUCCESS = "success";

    public static final String PAY_SUCCESS = "TRADE_SUCCESS";

    public static final String TRUE = "true";
    public static final String FAIL_INFORMATION = "fail";
}



