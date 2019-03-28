package com.anytec.sell.service.impl;

import com.anytec.sell.dataobject.ProductInfo;
import com.anytec.sell.dto.CartDTO;
import com.anytec.sell.enums.ProductStatusEnum;
import com.anytec.sell.enums.ResultEnum;
import com.anytec.sell.exception.SellException;
import com.anytec.sell.repository.ProductInfoRepository;
import com.anytec.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {

        Optional<ProductInfo> productInfo = repository.findById(productId);
        if(productInfo.isPresent()){
            return  productInfo.get();
        }
        return null;
    }

    @Override
    public List<ProductInfo> findUpAll() {

        return  repository.findByProductStatus(ProductStatusEnum.UP.getCode());

    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return repository.save(productInfo);
    }

    @Transactional
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO:cartDTOList) {
            Optional<ProductInfo> optionalProductInfo = repository.findById(cartDTO.getProductId());
            ProductInfo productInfo = optionalProductInfo.get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    //多线程扣库存可能存在超卖现象 使用redis的锁机制来改进
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO c : cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(c.getProductId());
            ProductInfo productInfo1 = productInfo.get();
            if(productInfo1 == null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result =  productInfo1.getProductStock() - c.getProductQuantity();
            if(result < 0){
                throw  new  SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo1.setProductStock(result);

            repository.save(productInfo1);
        }
    }
}
