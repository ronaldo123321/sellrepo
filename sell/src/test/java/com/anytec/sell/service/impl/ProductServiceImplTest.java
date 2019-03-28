package com.anytec.sell.service.impl;

import com.anytec.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {

        ProductInfo serviceOne = productService.findOne("123456");
        Assert.assertNotNull(serviceOne);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() {
        Pageable pageable =  PageRequest.of(0,10);
        Page<ProductInfo> productInfos = productService.findAll(pageable);
        System.out.println(productInfos.getTotalElements());
        Assert.assertNotEquals(0,productInfos.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("满汉全席");
        productInfo.setProductId("123457");
        productInfo.setCategoryType(6);
        productInfo.setProductPrice(new BigDecimal(6.2));
        productInfo.setProductDescription("good");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(1);
        productInfo.setProductStock(10);
        ProductInfo productInfo1 = productService.save(productInfo);
        Assert.assertNotNull(productInfo1);
    }
}