package com.anytec.sell.service.impl;

import com.anytec.sell.dataobject.OrderDetail;
import com.anytec.sell.dto.OrderDTO;
import com.anytec.sell.enums.OrderStatusEnum;
import com.anytec.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    public static  final String  BUYER_OPENID  =  "kdadadadedwq";

    public static final String ORDER_ID = "1553594546227827540";
    @Test
    public void createOrder() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小猪");
        orderDTO.setBuyerAddress("小渔村");
        orderDTO.setBuyerPhone("2120102912");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123457");
        orderDetail1.setProductQuantity(1);
        list.add(orderDetail);
        list.add(orderDetail1);

        orderDTO.setOrderDetailList(list);
        OrderDTO resule = orderService.createOrder(orderDTO);
        log.info("[create order] result = {}",resule);


    }

    @Test
    public void findOne() {

        OrderDTO serviceOne = orderService.findOne(ORDER_ID);
        log.info("[查询单个订单] result={}",serviceOne);
        Assert.assertEquals(ORDER_ID,serviceOne.getOrderId());

    }

    @Test
    public void findList() {

        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList( pageRequest);
//        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());

        Assert.assertTrue("查询所有的订单列表",orderDTOPage.getTotalElements() < 0);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void pay() {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.pay(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());

    }
}