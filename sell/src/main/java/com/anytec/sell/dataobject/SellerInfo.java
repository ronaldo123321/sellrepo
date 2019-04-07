package com.anytec.sell.dataobject;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class SellerInfo implements Serializable {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

}
