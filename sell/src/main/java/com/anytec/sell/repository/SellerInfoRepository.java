package com.anytec.sell.repository;

import com.anytec.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findSellerInfoByOpenid(String openid);

}
