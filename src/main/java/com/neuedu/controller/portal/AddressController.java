package com.neuedu.controller.portal;


import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/shopping/")
public class AddressController {

    @Autowired
    IAddressService addressService;

    /**
     * 地址添加
     * */

    @RequestMapping("add.do")
    public ServerResponse add(HttpSession session , Shipping shipping){
          UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }


        return addressService.add(userInfo.getId(),shipping);
    }

    /**
     * 地址删除
     * */

    @RequestMapping("del.do")
    public ServerResponse del(HttpSession session ,Integer shippingId){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }


        return addressService.del(userInfo.getId(),shippingId);
    }


    /**
     * 登录状态更新地址
     * */

    @RequestMapping("update.do")
    public ServerResponse update(HttpSession session ,Shipping shipping){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }

        shipping.setUserId(userInfo.getId());
        return addressService.update(shipping);
    }

    /**
     * 选中查看具体地址
     * */

    @RequestMapping("select.do")
    public ServerResponse select(HttpSession session ,Integer shippingId){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }


        return addressService.select(shippingId);
    }

    /**
     * 地址列表
     * */
    @RequestMapping("list.do")
    public ServerResponse list(HttpSession session,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
        }


        return addressService.list(pageNum,pageSize);
    }


}
