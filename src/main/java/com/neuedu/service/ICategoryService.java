package com.neuedu.service;


import com.neuedu.common.ServerResponse;

import javax.servlet.http.HttpSession;

public interface ICategoryService {

    /**
     * 获取类别中子类别（平级）
     */
   ServerResponse get_category(Integer categoryId);

    /**
     * 增加类别
     */
    ServerResponse add_category(Integer parent_Id,String categoryName);

    /**
     * 修改类别名称
     */
    ServerResponse set_category_name(Integer categoryId,String categoryName);

    /**
     * 获取当前分类id及递归子节点categoryId：递归必须要有一个结束条件，要不就变成子循环了
     * @param categotyId
     * @return
     */
    ServerResponse get_deep_category(Integer categotyId);

}
