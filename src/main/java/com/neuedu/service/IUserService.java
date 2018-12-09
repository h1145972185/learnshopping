package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;

public interface IUserService {

    /**
     * 注册接口
     * */
    public ServerResponse register(UserInfo userInfo);

    /**
     * 登录
     * */

    public ServerResponse login(String username,String password);

/**
 *检查用户名或邮箱是否有效
 */

    public ServerResponse check_valid(String str,String type);

    /**
     * 根据用户名查找密保问题
     * */

    public  ServerResponse forget_get_question(String username);

/**
 * 提交问题答案
 * */

     public  ServerResponse forget_check_answer(String username,String question,String answer);


     /**
      * 忘记密码的重设密码
      * */


    ServerResponse forget_reset_password(String username,String passwordNew,String forgetToken);

    /**
     * 登录后的修改密码
     * */

    public ServerResponse reset_password(UserInfo userInfo,String passwordOld, String passwordNew);


    /**
     * 后台管理员登陆
     * */
    public ServerResponse Adminlogin(String username,String password);
}
