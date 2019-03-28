package com.anytec.sell.dto;

import com.anytec.sell.dataobject.OrderDetail;
import com.anytec.sell.enums.OrderStatusEnum;
import com.anytec.sell.enums.PayStatusEnum;
import com.anytec.sell.utils.Date2LongSerizlizer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)  //返回的结果如果为null，则不返回
public class OrderDTO implements Serializable {


    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;
    /**
     * default new order
     */
    private Integer orderStatus;

    private Integer payStatus;

    //将自定义的Serizlizer应用至此
    @JsonSerialize(using = Date2LongSerizlizer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerizlizer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

//    @JsonIgnore//对象转换为json时自动忽略该字段
//    public OrderStatusEnum getOrderStatusEnum(){
//        //  method(orderStatus,OrderStatusEnum)
//    }
//
//    @JsonIgnore
//    public PayStatusEnum getPayStatusEnum(){
//
//    }
}
