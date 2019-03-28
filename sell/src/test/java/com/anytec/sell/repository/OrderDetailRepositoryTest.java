package com.anytec.sell.repository;

import com.anytec.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;


    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456810");
        orderDetail.setOrderId("111112");
        orderDetail.setProductId("1111114");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductName("皮蛋粥1");
        orderDetail.setProductPrice(new BigDecimal(1.4));
        orderDetail.setProductQuantity(2);
        OrderDetail save = repository.save(orderDetail);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOrderId() {

        List<OrderDetail> details = repository.findByOrderId("111112");
        Assert.assertNotEquals(0,details.size());
    }
}