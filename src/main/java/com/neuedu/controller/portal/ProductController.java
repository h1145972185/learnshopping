package com.neuedu.controller.portal;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product/")
public class ProductController {

    @Autowired
    IProductServic productServic;

    /**
     * 前台-商品详情
     * */
    @RequestMapping(value = "detail.do")
    public ServerResponse detail(Integer productId){
        return productServic.detail_portal(productId);
    }

    /**
     * 前台-搜索商品并排序
     * */
    @RequestMapping(value = "list.do")
    public ServerResponse list(@RequestParam(required = false) Integer categoryId,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false,defaultValue = "") String orderBy){
        return productServic.list(categoryId,keyword,pageNum,pageSize,orderBy);
    }
}
