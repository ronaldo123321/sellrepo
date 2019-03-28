package com.anytec.sell.dataobject;

import com.anytec.sell.enums.OrderStatusEnum;
import com.anytec.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**

 */
@Entity
@Table(name = "order_master")
@Data
@DynamicUpdate//自动更新time
public class OrderMaster implements Serializable {

      @Id
      private String orderId;

      private String buyerName;

      private String buyerPhone;

      private String buyerAddress;

      private String buyerOpenid;

      private BigDecimal orderAmount;
        /**
         * default new order
         */
      private Integer orderStatus = OrderStatusEnum.NEW.getCode();

      private Integer payStatus = PayStatusEnum.WAIT.getCode();

      private Date createTime;

      private Date updateTime;
}
