package com.anytec.sell.controller;

import com.anytec.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId){
        return secKillService.query(productId);
    }

    /**
     * 进行商品秒杀 未抢到则提示“”  已抢到则返回剩余库存量
     * @param productId
     * @return
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId){
        log.info("@skill request,productId:"+productId);
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.query(productId);
    }



}
