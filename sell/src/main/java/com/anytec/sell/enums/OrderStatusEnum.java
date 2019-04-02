package com.anytec.sell.enums;

import lombok.Getter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@Getter
public enum OrderStatusEnum implements CodeEnum {

    NEW(0,"新下单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消"),
    ;

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static OrderStatusEnum getOrderStatusEnum(Integer code){
        for(OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()){
            if (orderStatusEnum.getCode().equals(code)){
                return orderStatusEnum;
            }
        }
        return  null;
    }




}
