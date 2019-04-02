package com.anytec.sell.controller;

import com.anytec.sell.dto.OrderDTO;
import com.anytec.sell.enums.ResultEnum;
import com.anytec.sell.exception.SellException;
import com.anytec.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {


    @Autowired
    private OrderService orderService;


    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);

        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("order/list",map);

    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,Map<String,Object> map){

        OrderDTO orderDTO;
        try {
             orderDTO = orderService.findOne(orderId);
             orderService.cancel(orderDTO);
        } catch (SellException e){
            log.error("[卖家端发生异常],原因={}",e.getMessage());
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        map.put("url","sell/seller/order/list");
//        OrderDTO orderDTO1 = orderService.cancel(orderDTO);
         return new ModelAndView("common/success");
    }
}
