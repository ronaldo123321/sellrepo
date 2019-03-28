package com.anytec.sell.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {
    /**
     * 错误吗
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String msg;
    /**
     * 返回具体数据
     */
    private T date;

}
