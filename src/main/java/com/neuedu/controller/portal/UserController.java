package com.neuedu.controller.portal;


import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/portal/user/")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 登录
     * */
    @RequestMapping(value = "login.do")
    public ServerResponse login(HttpSession session, String username, String password) {

       ServerResponse serverResponse= userService.login(username,password);
       //判断登陆接口是否连接成功
        if (serverResponse.isSuccess()){//登陆成功后，保存登录状态
           session.setAttribute(Const.CURRENTUSER,serverResponse.getData());
        }
        return serverResponse;
    }

/**
 * 注册
 * */
    @RequestMapping(value = "register.do")
    public ServerResponse register( UserInfo userInfo) {
        return userService.register(userInfo);

    }

    /**
     * 检查用户名或邮箱是否有效
     * */
    @RequestMapping(value ="check_valid.do" )
    public ServerResponse check_valid(String str,String type){
        return userService.check_valid(str,type);

    }
    /**
     *获取登陆用户信息
     * */
    @RequestMapping(value = "get_user_info.do")
    public ServerResponse get_user_info(HttpSession session){

    Object o=session.getAttribute(Const.CURRENTUSER);
    if (o!=null&& o instanceof  UserInfo){
        UserInfo userInfo=(UserInfo)o;
        UserInfo responseUserInfo= new UserInfo();
        responseUserInfo.setId(userInfo.getId());
        responseUserInfo.setUsername(userInfo.getUsername());
        responseUserInfo.setPhone(userInfo.getPhone());
        responseUserInfo.setEmail(userInfo.getEmail());
        responseUserInfo.setUpdateTime(userInfo.getUpdateTime());
        responseUserInfo.setCreateTime(userInfo.getCreateTime());
        return ServerResponse.createServerResponseBySucess(null,responseUserInfo);

        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

    /**
     *获取当前登录用户的详细信息
     * */
    @RequestMapping(value = "get_information.do")
    public ServerResponse get_information(HttpSession session){

        Object o=session.getAttribute(Const.CURRENTUSER);
        if (o!=null&& o instanceof  UserInfo){
            UserInfo userInfo=(UserInfo)o;

            return ServerResponse.createServerResponseBySucess(null,userInfo);

        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

    /**
     * 根据用户名查询密保问题
     * */

      @RequestMapping(value = "forget_get_question.do")
    public ServerResponse forget_get_question(String username) {

        ServerResponse serverResponse=userService.forget_get_question(username);

        return serverResponse;

    }

    /**
     * 提交问题答案
     * */

    @RequestMapping(value = "forget_check_answer.do")
    public ServerResponse forget_check_answer(String username,String question,String answer) {

        ServerResponse serverResponse=userService.forget_check_answer(username,question,answer);

        return serverResponse;

    }


    /**
     * 忘记密码的重设密码
     * */

    @RequestMapping(value = "forget_reset_password.do")
    public ServerResponse forget_reset_password(String username,String passwordNew,String forgetToken) {

        ServerResponse serverResponse = userService.forget_reset_password(username, passwordNew, forgetToken);

        return serverResponse;

    }
    /**
     * 登陆状态的重置密码
     * */
    @RequestMapping(value = "reset_password.do")
    public ServerResponse reset_password(HttpSession session,String passwordOld,String passwordNew) {
        Object o=session.getAttribute(Const.CURRENTUSER);
        if (o!=null && o instanceof UserInfo){
            UserInfo userInfo=(UserInfo)o;
            return userService.reset_password(userInfo,passwordOld,passwordNew);
        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }
    /**
     * 退出登录
     * */
    @RequestMapping(value = "logout.do")
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENTUSER);
        return ServerResponse.createServerResponseBySucess();
    }
}
