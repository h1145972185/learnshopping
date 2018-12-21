package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.neuedu.VO.ProductDetailVO;
import com.neuedu.VO.ProductListVO;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IProductServic;
import com.neuedu.utils.DateUtils;
import com.neuedu.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductServic {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ICategoryService iCategoryService;
    /**
     * 增加or更新产品
     * */
    @Override
    public ServerResponse saveOrUpdate(Product product) {
        //1 参数非空交验
        if(product==null){
            return ServerResponse.createServerResponseByError("参数为空");
        }
        //2 设置商品主图sub_imges-->1 jpg 2 jpg 3 jpg
        String subImages= product.getSubImages();
        if (subImages!=null&&!subImages.equals("")){
            String [] subImageArr=subImages.split(",");
            if (subImageArr.length>0){
                //设置商品主图
                product.setMainImage(subImageArr[0]);
            }
        }

        //3 根据判断id是否为空来判断是添加还是更新
        if (product.getId()==null) {
            int result =productMapper.insert(product);
            if (result>0){
                return ServerResponse.createServerResponseBySucess();
            }else {
                return ServerResponse.createServerResponseByError("添加失败");
            }
        }else {
            //更新
            int result =productMapper.updateByPrimaryKey(product);
            if (result>0){
                return ServerResponse.createServerResponseBySucess();
            }else {
                return ServerResponse.createServerResponseByError("更新失败");
            }
        }
    }

    /**
     * 产品上下架
     * */

    @Override
    public ServerResponse set_sale_status(Integer productId, Integer status) {

        // 非空判断
        if(productId==null){
            return ServerResponse.createServerResponseByError("商品id参数不能为空");
        }
        if(status==null){
            return ServerResponse.createServerResponseByError("商品状态参数不能为空");
        }

        //更新商品状态
        Product product=new Product();
        product.setId(productId);
        product.setStatus(status);
        int result=productMapper.updateProductKeySelective(product);

       //  返回结果
        if (result>0){
                return ServerResponse.createServerResponseBySucess();
                }else {
                return ServerResponse.createServerResponseByError("更新失败");
        }
    }
    /**
     * 产品详情
     * */
    @Override
    public ServerResponse detail(Integer productId) {
        // 1 参数非空校验
        if(productId==null){
            return ServerResponse.createServerResponseByError("商品id参数不能为空");
        }
        // 2 查询product
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product==null){
            return ServerResponse.createServerResponseByError("商品不存在");
        }
        // 3 product-->productDetailVO
          ProductDetailVO productDetailVO = changeToVo(product);
        // 4 返回结果
        return ServerResponse.createServerResponseBySucess(null,productDetailVO);
    }



    public ProductDetailVO changeToVo(Product product){
        ProductDetailVO productDetailVO = new ProductDetailVO();
        productDetailVO.setCategoryId(product.getCategoryId());
        productDetailVO.setCreateTime(DateUtils.dateToStr(product.getCreateTime()));
        productDetailVO.setDetail(product.getDetail());
        productDetailVO.setId(product.getId());
        productDetailVO.setImageHost(PropertiesUtils.readByKey("imagesHost"));
        productDetailVO.setMainImage(product.getMainImage());
        productDetailVO.setName(product.getName());
        productDetailVO.setPrice(product.getPrice());
        productDetailVO.setStatus(product.getStatus());
        productDetailVO.setSubImages(product.getSubImages());
        productDetailVO.setStock(product.getStock());
        productDetailVO.setSubtitle(product.getSubtitle());
        productDetailVO.setUpdateTime(DateUtils.dateToStr(product.getUpdateTime()));
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category != null){
            productDetailVO.setParentCategoryId(category.getParent_Id());
        }
        return productDetailVO;
    }

    /**
     * 后台-商品列表，分页
     * */
    @Override
    public ServerResponse list(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        //1 查询商品数据 select * from product limit (pageNum-1)*pageSize.pageSize
        List<Product> productList=productMapper.selectAll();
        List<ProductListVO> productListVOList= Lists.newArrayList();
        if (productList!=null&&productList.size()>0){
            for (Product product:productList){
                ProductListVO productListVO=changListVo(product);
                productListVOList.add(productListVO);
            }
        }
        PageInfo pageInfo=new PageInfo(productListVOList);

        return ServerResponse.createServerResponseBySucess(null,pageInfo);
    }


    public ProductListVO changListVo(Product product){
        ProductListVO productListVO = new ProductListVO();
        productListVO.setCategoryId(product.getCategoryId());
        productListVO.setId(product.getId());
        productListVO.setMainImage(product.getMainImage());
        productListVO.setName(product.getName());
        productListVO.setPrice(product.getPrice());
        productListVO.setStatus(product.getStatus());
        productListVO.setSubtitle(product.getSubtitle());

        return productListVO;
    }

    /**
     * 后台-产品搜索
     * */

    @Override
    public ServerResponse search(Integer productId, String productName, Integer pageNum, Integer pageSize) {

        // select * from prudct where productId？and productName like %name%

        PageHelper.startPage(pageNum,pageSize);
        if (productName!=null&&productName.equals("")){
            productName="%"+productName+"%";
        }
   List<Product> productList=     productMapper.findProductByProductIdAndProductName(productId,productName);
        List<ProductListVO> productListVOList=Lists.newArrayList();
        if (productList!=null&&productList.size()>0){
            for (Product product:productList){
                ProductListVO productListVO=changListVo(product);
                productListVOList.add(productListVO);
            }
        }
        PageInfo pageInfo=new PageInfo(productListVOList);
        return ServerResponse.createServerResponseBySucess(null,pageInfo);
    }



    /**
     * 图片上传
     * */
    @Override
    public ServerResponse uploadPic(MultipartFile file, String path) {
        if(file==null){
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
        String oldFileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString();
        newFileName += oldFileName.substring(oldFileName.lastIndexOf("."));
        File result = new File(path,newFileName);
        try {
            file.transferTo(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> result_map = Maps.newHashMap();
        result_map.put("uri",newFileName);
        result_map.put("url",PropertiesUtils.readByKey("imagesHost")+newFileName);
        return ServerResponse.createServerResponseBySucess(null,result_map);
    }


    /**
     * 前台-商品详情
     * */

    @Override
    public ServerResponse detail_portal(Integer productId) {

        //  参数非空校验
        if (productId==null){
            return ServerResponse.createServerResponseByError("商品id不能为空");
        }
        //  查询product
        Product product= productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return ServerResponse.createServerResponseByError("商品不存在");
        }
        //  校验商品状态
        if (product.getStatus()!=ResponseCode.ON_SALE.getStatus()){
            return ServerResponse.createServerResponseByError("商品已经下架或者删除");
        }
        //  获取productDetailVO
        ProductDetailVO productDetailVO=changeToVo(product);

        //  返回结果
        return ServerResponse.createServerResponseBySucess(null,productDetailVO);
    }

    /**
     * 前台-商品搜索并排序
     * */

    @Override
    public ServerResponse list(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        // 参数校验 categoryId和 keyword不能同时为空
            if (categoryId==null&&(keyword==null||keyword.equals(""))){
                return ServerResponse.createServerResponseByError("参数报错");
            }
        // categoryId
        Set<Integer> integerSet= Sets.newHashSet();
            if (categoryId!=null){
                Category category=categoryMapper.selectByPrimaryKey(categoryId);
                if (category==null&&(keyword==null||keyword.equals(""))){
                    //说明没有商品数据
                    PageHelper.startPage(pageNum,pageSize);
                    List<ProductListVO> productListVOList = Lists.newArrayList();
                    PageInfo pageInfo=new PageInfo(productListVOList);
                    return ServerResponse.createServerResponseBySucess(null,pageInfo);
                }

                ServerResponse serverResponse = iCategoryService.get_deep_category(categoryId);

                if (serverResponse.isSuccess()){
                 integerSet=(Set<Integer>)serverResponse.getData();

                }
            }
        //  keyword
        if (keyword!=null&&!keyword.equals("")){
            keyword="%"+keyword+"%";
        }
        if (orderBy.equals("")){
            PageHelper.startPage(pageNum,pageSize);
        }else {
        String[] orderByArr=  orderBy.split("_");
        if (orderByArr.length>1){
            PageHelper.startPage(pageNum,pageSize,orderByArr[0]+""+orderByArr[1]);
        }
        }

        //  List<Product>  -->List<ProductListVO>
        List<Product> productList= productMapper.searchProduct(integerSet,keyword);
            List<ProductListVO> productListVOList=Lists.newArrayList();
            if (productList!=null&&productList.size()>0){
                for (Product product:productList){
                    ProductListVO productListVO= changListVo(product);
                    productListVOList.add(productListVO);
                }
            }
        //  分页

        PageInfo pageInfo=new PageInfo();
            pageInfo.setList(productListVOList);

        //  返回
        return ServerResponse.createServerResponseBySucess(null,pageInfo);


    }

}
