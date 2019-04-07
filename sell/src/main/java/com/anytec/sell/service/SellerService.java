package com.anytec.sell.service;

import com.anytec.sell.dataobject.SellerInfo;

public interface SellerService {

    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
