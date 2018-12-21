package com.neuedu.service;

import com.neuedu.common.ServerResponse;

public interface ICartService {


/**
 *  购物车的增删改查
 * */
    public ServerResponse add(Integer userId,Integer productId,Integer count);



    /**
     * 购物车List列表
     */
    ServerResponse list(Integer userId);

    /**
     * 更新购物车某个产品数量
     */
    ServerResponse update(Integer userId, Integer productId, Integer count);

    /**
     * 移除购物车某个产品
     */
    ServerResponse delete_product(Integer userId, String productIds);

    /**
     * 购物车取消选中某个产品
     *
     * @param userId
     * @param productId
     * @return
     */
    ServerResponse select(Integer userId, Integer productId, Integer checked);

    /**
     * 统计用户购物车中的数量
     *
     * @param userId
     * @return
     */
    ServerResponse get_cart_product_count(Integer userId);
}
