package com.anytec.sell.repository;

import com.anytec.sell.dataobject.ProductCategroy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<对象，主键类型>
public interface ProductCategroyRepository extends JpaRepository<ProductCategroy,Integer> {


    List<ProductCategroy> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
