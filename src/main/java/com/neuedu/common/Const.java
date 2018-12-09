package com.neuedu.common;

public class Const {
    public static final Integer SUCCESS_CODE=0;
    public static final Integer SUCCESS_ERROR=1;

    public static final Integer USER_ROLE_CUSTOMER=0;//普通用户
    public static final Integer USER_ROLE_ADMIN=1;//管理员


    //判断是否登录时 将账户信息用session存储时的 setAttribute的k值
    public static final String  CURRENTUSER="CURRENTUSER";

    public static final String USERNAME="username";
    public static final String EMAIL="email";

}
