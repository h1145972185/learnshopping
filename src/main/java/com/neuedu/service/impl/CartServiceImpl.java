package com.neuedu.service.impl;

import com.google.common.collect.Lists;
import com.neuedu.VO.CartProductVO;
import com.neuedu.VO.CartVO;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CartMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICartService;
import com.neuedu.utils.BigDecimalUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;


    /**
     * 商品的添加与更新
     * */
    @Override
    public ServerResponse add(Integer userId, Integer productId, Integer count) {
//        非空校验
        if (productId == null || count == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(), ResponseCode.PARAM_EMPTY.getMsg());
        }
//        添加商品信息
        Cart cart = cartMapper.selectCartByUseridAndProductid(userId, productId);
        if (cart == null) {
//            添加
            Cart cart1 = new Cart();
            cart1.setProductId(productId);
            cart1.setUserId(userId);
            cart1.setQuantity(count);
            cart1.setChecked(ResponseCode.PRODUCT_CHECKED.getStatus());
            int result = cartMapper.insert(cart1);
            if (result <= 0) {
                return ServerResponse.createServerResponseByError(ResponseCode.LIMIT_NUM_FAIL.getStatus(), ResponseCode.LIMIT_NUM_FAIL.getMsg());
            }
        } else {
//            更新
            Cart cart1 = new Cart();
            cart1.setId(cart.getId());
            cart1.setChecked(cart.getChecked());
            cart1.setQuantity(cart.getQuantity()+count);
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            int update_result = cartMapper.updateByPrimaryKey(cart1);
            if (update_result <= 0) {
                return ServerResponse.createServerResponseByError(ResponseCode.UPDATE_FAIL.getStatus(), ResponseCode.UPDATE_FAIL.getMsg());
            }
        }
        return ServerResponse.createServerResponseBySucess(null, getCartVOLimit(userId));
    }
//高复用

    public CartVO getCartVOLimit(Integer userId){
        CartVO cartVO=new CartVO();
        //根据userid查询购物信息--》list<cart>
       List<Cart> cartList= cartMapper.selectCartByUserid(userId);
        //List<Cart>-->List<CartProduct>
        List<CartProductVO> cartProductVOList= Lists.newArrayList();

        //购物车总价格
        BigDecimal carttotalprice=new BigDecimal("0");

        if (cartList!=null&&cartList.size()>0){
            for (Cart cart:cartList){
                CartProductVO cartProductVO=new CartProductVO();
                cartProductVO.setId(cart.getId());
                cartProductVO.setQuantity(cart.getQuantity());
                cartProductVO.setUserId(cart.getUserId());
                cartProductVO.setProductChecked(cart.getChecked());
                //查询商品
             Product product=   productMapper.selectByPrimaryKey(cart.getProductId());
                 if (product!=null)
                 {
                     cartProductVO.setProductId(cart.getProductId());
                     cartProductVO.setProductMainImage(product.getMainImage());
                     cartProductVO.setProductName(product.getName());
                     cartProductVO.setProductPrice(product.getPrice());
                     cartProductVO.setProductStatus(product.getStatus());
                     cartProductVO.setProductStock(product.getStock());
                     cartProductVO.setProductSubtitle(product.getSubtitle());
                     cartProductVO.setProductChecked(cart.getChecked());
                     int stock=  product.getStock();
                     int limitProductCount=0;
                     if (stock>=cart.getQuantity()){
                         limitProductCount=cart.getQuantity();
                         cartProductVO.setLimitQuantity("LIMIT_NUM_SUCCESS");
                     }else {
                         limitProductCount=stock;
                         //更新购物车中商品数量
                         Cart cart1=new Cart();
                         cart1.setId(cart.getId());
                         cart1.setQuantity(stock);
                         cart1.setProductId(cart.getProductId());
                         cart1.setChecked(cart.getChecked());
                         cart1.setUserId(cart.getUserId());

                         cartMapper.updateByPrimaryKey(cart1);

                         cartProductVO.setLimitQuantity("LIMIT_NUM_FAIL ");
                     }
                     cartProductVO.setQuantity(limitProductCount);
                     cartProductVO.setProductTotalPrice(BigDecimalUtils.mul(product.getPrice().doubleValue(),Double.valueOf(cartProductVO.getQuantity())));
                 }
                  /*  if (cart.getChecked()==1){

                        carttotalprice=  BigDecimalUtils.add(carttotalprice.doubleValue(),cartProductVO.getProductTotalPrice().doubleValue());

                    }*/

                cartProductVOList.add(cartProductVO);
            }
        }
        cartVO.setCartProductVOList(cartProductVOList);

        // 计算总构架
        cartVO.setCarttotalprice(carttotalprice);
        // 判断购物车是否全选
            int count=cartMapper.isCheckedAll(userId);
            if (count>0){
                cartVO.setIsallchecked(false);
            }else {
                cartVO.setIsallchecked(true);
            }
        // 返回结果

        return cartVO;
    }

    /**
     * 购物车List列表
     */
    @Override
    public ServerResponse list(Integer userId) {

        CartVO cartVO=getCartVOLimit(userId);
        return ServerResponse.createServerResponseBySucess(null,cartVO);
    }
    /**
     * 更新购物车某个产品数量
     */
    @Override
    public ServerResponse update(Integer userId, Integer productId, Integer count) {
        //step1：参数的校验
        if(productId==null || count==null){
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        //step2：查询购物车中商品
        Cart cart = cartMapper.selectCartByUseridAndProductid(userId,productId);
        if (cart!=null){
            //step3：更新数量
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKey(cart);
        }

        //step4：返回结果
        return ServerResponse.createServerResponseBySucess(null,getCartVOLimit(userId));
    }
    /**
     * 移除购物车某个产品
     */
    @Override
    public ServerResponse delete_product(Integer userId, String productIds) {
        //step1：参数的非空校验
        if (StringUtils.isBlank(productIds)){
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        //step2：productIds --> List<Integer>
        List<Integer>productIdList = Lists.newArrayList();
        String [] productIdArr = productIds.split(",");
        if (productIdArr!=null&&productIdArr.length>0){
            for (String productStr:productIdArr)  {
                Integer productId = Integer.parseInt(productStr);
                productIdList.add(productId);

            }
        }
        //step3：调用dao
        cartMapper.deleteByUserAndProductIds(userId,productIdList);
        //step4：返回结果
        return ServerResponse.createServerResponseBySucess(null,getCartVOLimit(userId));
    }
    /**
     * 购物车取消选中某个产品
     * @param userId
     * @param productId
     * @return
     */
    @Override
    public ServerResponse select(Integer userId, Integer productId,Integer checked) {

        //step1：非空校验
        /*if (productId==null){
            return ServerResponse.createServerResponseByErrow("参数不能为空");
        }*/
        //step2：dao接口
        cartMapper.selectOrUnselectProduct(userId,productId,checked);

        //step3：返回结果
        return ServerResponse.createServerResponseBySucess(null,getCartVOLimit(userId));
    }
    /**
     * 统计用户购物车中的数量
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse get_cart_product_count(Integer userId) {
        int quantity = cartMapper.get_cart_product_count(userId);
        return ServerResponse.createServerResponseBySucess(null,quantity);
    }
}

