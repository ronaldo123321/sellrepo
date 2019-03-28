package com.anytec.sell.controller;

import com.anytec.sell.config.WechatAccountConfig;
import com.anytec.sell.enums.ResultEnum;
import com.anytec.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {


    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig config;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1.配置
       String redirectUrl =  wxMpService.oauth2buildAuthorizationUrl(config.getRedirectUrl(),
                WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        //2.调用方法

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new  WxMpOAuth2AccessToken();
        try {
             wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);

        } catch (WxErrorException e) {
            log.error("[微信网页授权]{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
          String openId =  wxMpOAuth2AccessToken.getOpenId();
         log.info("openId={}",openId);
        return "redirect:" + returnUrl + "?openid="  + openId;

    }
}
