package com.anytec.sell.utils;

import com.anytec.sell.enums.CodeEnum;

public class EnumUtils {

    public static <T extends CodeEnum>T getByCode(Integer code, Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

}
