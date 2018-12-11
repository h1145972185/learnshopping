package com.neuedu.controller.manage;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/user/")
public class ManageController {

    @Autowired
    IUserService userService;

    /**
     * 后台管理员登陆
     * */

    @RequestMapping(value = "login.do")
    public ServerResponse login(HttpSession session, String username, String password) {

        ServerResponse serverResponse= userService.login(username,password);
        //判断登陆接口是否连接成功

        if (serverResponse.isSuccess()){//登陆成功后，保存登录状态
            if (Const.USER_ROLE_ADMIN==0)
            {
             return ServerResponse.createServerResponseByError("权限过低，禁止访问！");
            }
            session.setAttribute(Const.CURRENTUSER,serverResponse.getData());
        }
        return serverResponse;
    }

}
