package com.neuedu.controller.manage;

import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/category/")
public class CategoryManageController {

    @Autowired
    ICategoryService iCategoryService;

    /**
     * 获取类别子节点(平级)
     */
    @RequestMapping(value = "get_category.do")
    public ServerResponse get_category(HttpSession session,Integer categoryId){
        Object object = session.getAttribute(Const.CURRENTUSER);
        if (object != null && object instanceof UserInfo){
            UserInfo userInfo = (UserInfo) object;
            //判断用户的权限
            if (userInfo.getRole() != Const.USER_ROLE_ADMIN){
                return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(),ResponseCode.NOT_AUTHORITY.getMsg());
            }
            return iCategoryService.get_category(categoryId);
        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

    /**
     * 增加节点
     */
    @RequestMapping(value = "add_category.do")
    public ServerResponse add_category(HttpSession session, @RequestParam(value = "parentId", required = false,defaultValue = "0") Integer parentId, String categoryName){
        Object object = session.getAttribute(Const.CURRENTUSER);
        if (object != null && object instanceof UserInfo){
            UserInfo userInfo = (UserInfo) object;
            //判断用户的权限
            if (userInfo.getRole() != Const.USER_ROLE_ADMIN){
                return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(),ResponseCode.NOT_AUTHORITY.getMsg());
            }
            return iCategoryService.add_category(parentId,categoryName);
        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

    /**
     * 修改类别名字接口
     */
    @RequestMapping(value = "set_category_name.do")
    public ServerResponse set_category_name(HttpSession session, Integer categoty_Id, String categoryName){
        Object object = session.getAttribute(Const.CURRENTUSER);
        if (object != null && object instanceof UserInfo){
            UserInfo userInfo = (UserInfo) object;
            //判断用户的权限
            if (userInfo.getRole() != Const.USER_ROLE_ADMIN){
                return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(),ResponseCode.NOT_AUTHORITY.getMsg());
            }
            return iCategoryService.set_category_name(categoty_Id,categoryName);
        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

    /**
     * 获取当前分类id及递归子节点categoryId
     */
    @RequestMapping(value = "get_deep_category.do")
    public ServerResponse get_deep_category(HttpSession session, Integer categotyId){
        Object object = session.getAttribute(Const.CURRENTUSER);
        if (object != null && object instanceof UserInfo){
            UserInfo userInfo = (UserInfo) object;
            //判断用户的权限
            if (userInfo.getRole() != Const.USER_ROLE_ADMIN){
                return ServerResponse.createServerResponseByError(ResponseCode.NOT_AUTHORITY.getStatus(),ResponseCode.NOT_AUTHORITY.getMsg());
            }
            return iCategoryService.get_deep_category(categotyId);
        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

}
