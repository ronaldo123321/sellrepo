package com.anytec.sell.dataobject;

import com.anytec.sell.enums.ProductStatusEnum;
import com.anytec.sell.utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product_info")
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = -4338121525897509523L;

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;
    /**
     * 0正常 1已下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtils.getByCode(productStatus,ProductStatusEnum.class);
    }
}
