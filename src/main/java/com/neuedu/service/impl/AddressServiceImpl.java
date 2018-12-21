package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ShippingMapper;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IAddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    ShippingMapper shippingMapper;
    /**
     * 地址添加
     * */

    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {

        // 参数校验
        if (shipping==null)
        {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        // 添加
        shipping.setUserId(userId);
        shippingMapper.insert(shipping);


        // 返回结果
        Map<String,Integer>map= Maps.newHashMap();
        map.put("shippingId",shipping.getId());

        return ServerResponse.createServerResponseBySucess(null,map);
    }
    /**
     * 删除地址
     * */
    @Override
    public ServerResponse del(Integer userId, Integer shippingId) {
        // 参数校验
        if (shippingId==null)
        {
            return ServerResponse.createServerResponseByError("参数错误");
        }

      int result=shippingMapper.deleteByUserIdAndShippingId(userId,shippingId);


        if (result>0){
            return ServerResponse.createServerResponseBySucess();
        }

        return ServerResponse.createServerResponseByError("删除失败");
    }

    /**
     * 登陆状态下更新地址
     * */

    @Override
    public ServerResponse update( Shipping shipping) {



        // 非空判断

        if (shipping==null)
        {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        // 更新
        int result=shippingMapper.updateBySelectiveKey(shipping);

        if (result>0){
            return ServerResponse.createServerResponseBySucess();
        }

        //返回结果
        return ServerResponse.createServerResponseByError("更新失败！");
    }
    /**
     * 选中查看具体地址
     * */

    @Override
    public ServerResponse select(Integer shippingId) {


        // 非空判断

        if (shippingId==null)
        {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        Shipping shipping=shippingMapper.selectByPrimaryKey(shippingId);
        return ServerResponse.createServerResponseBySucess(null,shipping);
    }
    /**
     * 地址列表
     * */

    @Override
    public ServerResponse list(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList=shippingMapper.selectAll();
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createServerResponseBySucess(null,pageInfo);
    }
}
