package com.anytec.sell.service.impl;

import com.anytec.sell.dataobject.ProductCategroy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategroyServiceImplTest {

    @Autowired
    private ProductCategroyServiceImpl categroyService;

    @Test
    public void findOne() {
        ProductCategroy one = categroyService.findOne(1);
        Assert.assertEquals(new Integer(1),one.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategroy> all = categroyService.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findByCategoryTypeIn() {

        List<ProductCategroy> byCategoryTypeIn = categroyService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
       Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    public void save() {

        ProductCategroy productCategroy = new ProductCategroy();
        productCategroy.setCategoryName("专享区");
        productCategroy.setCategoryType(7);
        ProductCategroy categroy = categroyService.save(productCategroy);
        Assert.assertNotNull(categroy);
    }
}