package org.vwvm.store.services.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.vwvm.store.beans.bean.ProductBean;
import org.vwvm.store.beans.entity.Product;
import org.vwvm.store.beans.entity.ProductImg;
import org.vwvm.store.beans.entity.ProductParams;
import org.vwvm.store.beans.entity.ProductSku;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.mappers.mapper.ProductImgMapper;
import org.vwvm.store.mappers.mapper.ProductMapper;
import org.vwvm.store.mappers.mapper.ProductParamsMapper;
import org.vwvm.store.mappers.mapper.ProductSkuMapper;
import org.vwvm.store.services.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductBean> implements IProductService {

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductImgMapper productImgMapper;
    @Resource
    private ProductSkuMapper productSkuMapper;
    @Resource
    private ProductParamsMapper productParamsMapper;


    @Override
    public ResultVO recommendProductsList() {
        List<ProductBean> productBeans = productMapper.selectRecommendProducts();
        return new ResultVO(ResStatus.OK, "success", productBeans);
    }

    /**
     * @param productId 商品id
     * @return 获取商品详细信息
     * 商品详细信息
     * 无需事务，可重复读取
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResultVO getProductBasicInfo(String productId) {
        //1.商品基本信息,product_status = 1表示上架商品
        List<ProductBean> products = productMapper.selectByMap(Map.of("product_id", productId, "product_status", 1));
        if (products.size() > 0) {
            //商品图片
            List<ProductImg> productImgs = productImgMapper.selectByMap(Map.of("item_id", productId));
            //商品套餐
            List<ProductSku> productSkus = productSkuMapper.selectByMap(Map.of("product_id", productId, "status", 1));

            return new ResultVO(ResStatus.OK, "success", Map.of("products", products, "productImgs", productImgs, "productSkus", productSkus));
        } else {
            return new ResultVO(ResStatus.NO, "商品不存在", null);
        }
    }

    @Override
    public ResultVO getProductParamsById(String productId) {
        List<ProductParams> productParams = productParamsMapper.selectByMap(Map.of("product_id", productId));
        if (productParams.size() > 0){
            return new ResultVO(ResStatus.OK, "success", productParams.get(0));
        }
        return new ResultVO(ResStatus.NO, "商品三无", null);
    }

    @Override
    public ResultVO productTop6(String pid) {

        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        return new ResultVO(ResStatus.NO, "查询成功", productMapper);
    }

    @Override
    public ResultVO selectCategoryProducts(Integer id) {
        return new ResultVO(ResStatus.NO, "查询成功", productMapper.selectCategoryProducts(id));
    }
}