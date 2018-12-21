package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import org.omg.PortableInterceptor.INACTIVE;

public interface IAddressService {


    /**
     * 地址添加
     * */

    public ServerResponse add(Integer userId , Shipping shipping);

/**
 *
 * 删除地址
 */

    ServerResponse del(Integer userId, Integer shippingId);

    /**
     * 登录状态下更新地址
     * */
   ServerResponse update(Shipping shipping);

   /**
    * 选中查看具体地址
    * */

      ServerResponse select(Integer shippingId);

      /**
       * 地址列表
       * */


    ServerResponse list( Integer pageNum, Integer pageSize);
}
