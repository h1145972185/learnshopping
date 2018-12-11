package com.neuedu.controller.manage;


import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IProductServic;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/product/")
public class ProductManageController {
    @Autowired
    IProductServic productServic;

    /**
     * 新增OR更新产品
     */
    @RequestMapping(value = "saveOrUpdate.do")
    public ServerResponse saveOrUpdate(HttpSession session, Product product) {

        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(), ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //判断用户权限
        if (userInfo.getRole() != Const.USER_ROLE_ADMIN) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(), ResponseCode.NOT_AUTHORITY.getMsg());
        }
        return productServic.saveOrUpdate(product);
    }

    /**
     * 商品上下架
     */

    @RequestMapping(value = "set_sale_status.do")
    public ServerResponse set_sale_status(HttpSession session, Integer productId, Integer status) {

        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(), ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //判断用户权限
        if (userInfo.getRole() != Const.USER_ROLE_ADMIN) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(), ResponseCode.NOT_AUTHORITY.getMsg());
        }
        return productServic.set_sale_status(productId, status);
    }


    /**
     * 查看商品详情
     */
    @RequestMapping(value = "detail.do")
    public ServerResponse detail(HttpSession session, Integer productId) {

        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(), ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //判断用户权限
        if (userInfo.getRole() != Const.USER_ROLE_ADMIN) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(), ResponseCode.NOT_AUTHORITY.getMsg());
        }
        return productServic.detail(productId);
    }

    /**
     * 查看产品list
     */
    @RequestMapping(value = "list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize) {

        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(), ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //判断用户权限
        if (userInfo.getRole() != Const.USER_ROLE_ADMIN) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(), ResponseCode.NOT_AUTHORITY.getMsg());
        }
        return productServic.list(pageNum,pageSize);
    }
    /**
     * 后台-产品搜索
     * */


    @RequestMapping(value = "search.do")
    public ServerResponse search(HttpSession session,
                                 @RequestParam(value = "productId",required = false)Integer productId,
                                 @RequestParam(value = "productName",required = false)String productName,
                                 @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize) {

        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(), ResponseCode.USER_NOT_LOGIN.getMsg());
        }
        //判断用户权限
        if (userInfo.getRole() != Const.USER_ROLE_ADMIN) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(), ResponseCode.NOT_AUTHORITY.getMsg());
        }
        return productServic.search(productId,productName,pageNum,pageSize);
    }

    /**
     * 后台-图片上传
     * */

}
