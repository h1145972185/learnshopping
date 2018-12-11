package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import org.springframework.web.multipart.MultipartFile;


public interface IProductServic {

    /**
     * 新增or跟新产品
     * */
    ServerResponse saveOrUpdate(Product product);

    /**
     * 产品上下架
     * @param productId 商品id
     * @param status 商品状态
     * */
    ServerResponse set_sale_status(Integer productId,Integer status);

    /**
     * 产品详情
     * */
    ServerResponse detail(Integer productId);


    /**
     * 后台-商品列表-分页
     * */
    ServerResponse list(Integer pageNum,Integer pageSize);

    /**
     * 后台-产品搜索
     * */
   ServerResponse search(Integer productId,String productName,Integer pageNum,Integer pageSize);

    /**
     * 上传图片
     * @param file
     * @param path
     * @return
     */
    ServerResponse uploadPic(MultipartFile file, String path);

}
