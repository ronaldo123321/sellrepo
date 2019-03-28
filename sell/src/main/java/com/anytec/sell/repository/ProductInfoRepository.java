package com.anytec.sell.repository;

import com.anytec.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    /**
     * 查询已上架的商品
     * @param productStatus
     * @return
     */
     List<ProductInfo> findByProductStatus(Integer productStatus);
}
