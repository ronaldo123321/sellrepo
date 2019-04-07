package com.anytec.sell.service.impl;

import com.anytec.sell.dataobject.SellerInfo;
import com.anytec.sell.repository.SellerInfoRepository;
import com.anytec.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        return  sellerInfoRepository.findSellerInfoByOpenid(openid);
    }
}
