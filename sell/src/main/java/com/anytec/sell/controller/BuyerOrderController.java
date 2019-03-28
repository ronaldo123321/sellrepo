package com.anytec.sell.controller;

import com.anytec.sell.VO.ResultVO;
import com.anytec.sell.convertor.OrderForm2OrderDTOConverter;
import com.anytec.sell.dto.OrderDTO;
import com.anytec.sell.enums.ResultEnum;
import com.anytec.sell.exception.SellException;
import com.anytec.sell.form.OrderForm;
import com.anytec.sell.service.BuyerService;
import com.anytec.sell.service.OrderService;
import com.anytec.sell.utils.ResultVOUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                log.error("【创建订单】参数不正确,orderForm={}",orderForm);
                throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                        bindingResult.getFieldError().getDefaultMessage());
            }
            OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
            if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
                log.error("【创建订单】购物车不能为空");
                throw new SellException(ResultEnum.CART_EMPTY);
            }
            OrderDTO resultDTO = orderService.createOrder(orderDTO);
            Map<String,String> map = new HashMap<>();
            map.put("orderId",resultDTO.getOrderId());
            return ResultVOUtils.success(map);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest =  PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);

        return ResultVOUtils.success(orderDTOPage.getContent());

    }

    //订单详情
    @GetMapping("/detail")
    public  ResultVO<OrderDTO> detail(@RequestParam(value = "openid") String openid,
                                      @RequestParam("orderId") String orderId){
       //将所有不安全的业务判断逻辑放在service层处理
        OrderDTO orderOne = buyerService.findOrderOne(openid, orderId);

        return ResultVOUtils.success(orderOne);
    }

    //订单取消
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam(value = "openid") String openid,
                           @RequestParam("orderId") String orderId){

        buyerService.cancelOrder(openid,orderId);

        return ResultVOUtils.success();
    }
}
