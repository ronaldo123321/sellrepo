package com.anytec.sell.dataobject.mapper;


import com.anytec.sell.dataobject.ProductCategroy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByObject() {

        ProductCategroy productCategroy = new ProductCategroy();
        productCategroy.setCategoryName("师兄最不爱");
        productCategroy.setCategoryType(1000);
        int result = mapper.insertByObject(productCategroy);
        Assert.assertEquals(1,result);

    }

    @Test
    public void select() {
        ProductCategroy byCategoryType = mapper.findByCategoryType(1000);
        Assert.assertNotNull(byCategoryType);
    }
}