package com.anytec.sell.repository;

import com.anytec.sell.dataobject.OrderMaster;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByBuyerOpenid() {
        Pageable pageRequest = PageRequest.of(1, 3);
        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid("kevin123", pageRequest);
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
        System.out.println(orderMasters.getContent().toString());
    }

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerOpenid("kevin123");
        orderMaster.setBuyerName("大师兄1");
        orderMaster.setBuyerPhone("18927890923");
        orderMaster.setBuyerAddress("上海");
        orderMaster.setOrderAmount(new BigDecimal(1.3));
        OrderMaster save = repository.save(orderMaster);
        Assert.assertNotNull(save);
    }
}