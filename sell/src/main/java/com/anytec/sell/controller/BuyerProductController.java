package com.anytec.sell.controller;

import com.anytec.sell.VO.ProductInfoVO;
import com.anytec.sell.VO.ProductVO;
import com.anytec.sell.VO.ResultVO;
import com.anytec.sell.dataobject.ProductCategroy;
import com.anytec.sell.dataobject.ProductInfo;
import com.anytec.sell.service.ProductCategroyService;
import com.anytec.sell.service.ProductService;
import com.anytec.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategroyService productCategroyService;


    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO getBuyerProduct(){
        //1.选择已上架的商品
        List<ProductInfo> productInfos = productService.findUpAll();
        //2.一次性选择商品类目列表
        List<Integer> categoryList = productInfos.stream().
                map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategroy> productCategoryList = productCategroyService.findByCategoryTypeIn(categoryList);
        //3.组装数据
        List<ProductVO> productVOS = new ArrayList<>();

        for (ProductCategroy pc : productCategoryList) {
             ProductVO productVO = new ProductVO();
             productVO.setCategoryName(pc.getCategoryName());
             productVO.setCategoryType(pc.getCategoryType());
             List<ProductInfoVO> productInfoVOList = new ArrayList<>();
             for (ProductInfo productInfo: productInfos) {
                if(productInfo.getCategoryType().equals(pc.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
             }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOS.add(productVO);
        }

        return ResultVOUtils.success(productVOS);
    }



    @GetMapping("/test")
    public ResultVO test(){
        ResultVO objectResultVO = new ResultVO();

        ProductInfoVO productInfoVO = new ProductInfoVO();
        ProductVO productVO = new ProductVO();
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        objectResultVO.setCode(0);
        objectResultVO.setMsg("success");
        objectResultVO.setDate(Arrays.asList(productVO));
        return objectResultVO;
    }
}
