package com.anytec.sell.form;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductForm implements Serializable {


    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;
    /**
     * 0正常 1已下架
     */
    //private Integer productStatus;

    private Integer categoryType;
}
