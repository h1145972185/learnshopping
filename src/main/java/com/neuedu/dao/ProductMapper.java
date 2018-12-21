package com.neuedu.dao;

import com.neuedu.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbg.generated
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbg.generated
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbg.generated
     */
    List<Product> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Product record);

    /**
     * 商品上下架
     * */

    int updateProductKeySelective(Product product);

    /**
     * 按照productId、productName 查询
     * */
    List<Product> findProductByProductIdAndProductName(@Param("productId") Integer productId,
                                                       @Param("productName") String productName);


    /**
     * 前台接口-搜索商品
     * */
    List<Product> searchProduct(@Param("integerSet") Set<Integer> integerSet,
                                @Param("keyword")String keyword);
}