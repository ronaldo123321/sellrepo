package com.anytec.sell.service;

import com.anytec.sell.dataobject.ProductCategroy;

import java.util.List;

public interface ProductCategroyService {

    ProductCategroy findOne(Integer categoryId);

    List<ProductCategroy> findAll();

    List<ProductCategroy> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategroy save(ProductCategroy productCategroy);
}
