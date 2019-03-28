package com.anytec.sell.repository;

import com.anytec.sell.dataobject.ProductCategroy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategroyRepositoryTest {

    @Autowired
    private ProductCategroyRepository repository;

    @Test
    public void findOneTest(){
        Optional<ProductCategroy>  productCategroy = repository.findById(1);
        System.out.println(productCategroy.get().toString());
    }


    @Test
    @Transactional
    public  void  saveTest(){
        ProductCategroy productCategroy = new ProductCategroy();
       // productCategroy.setCategoryId(2);
        productCategroy.setCategoryName("女生最爱1");
        productCategroy.setCategoryType(34);
        repository.save(productCategroy);
    }

    @Test
    public  void  updateTest(){
        Optional<ProductCategroy>  productCategroy = repository.findById(2);
        ProductCategroy productCategroy1 = productCategroy.get();
        productCategroy1.setCategoryType(6);

        ProductCategroy productCategroy2 = repository.save(productCategroy1);
        Assert.assertNotNull(productCategroy2);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);

        List<ProductCategroy> result = repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,result.size());
    }
}