package com.anytec.sell.service.impl;

import com.anytec.sell.dataobject.ProductCategroy;
import com.anytec.sell.repository.ProductCategroyRepository;
import com.anytec.sell.service.ProductCategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategroyServiceImpl implements ProductCategroyService {

    @Autowired
    private ProductCategroyRepository repository;

    @Override
    public ProductCategroy findOne(Integer categoryId) {
        Optional<ProductCategroy> cate = repository.findById(categoryId);
        if(cate.isPresent()){
           return cate.get();
        }
        return null;
    }

    @Override
    public List<ProductCategroy> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategroy> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategroy save(ProductCategroy productCategroy) {
        return repository.save(productCategroy);
    }
}
