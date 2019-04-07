package com.anytec.sell.handler;

import com.anytec.sell.VO.ResultVO;
import com.anytec.sell.config.ProjectUrl;
import com.anytec.sell.exception.SellException;
import com.anytec.sell.exception.SellerAuthorizeException;
import com.anytec.sell.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrl projectUrl;

    //拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        //地址拼接，略(应该重定向到扫码界面)
        return new ModelAndView("redirect:"+"http://www.baidu.com");
//        return new ModelAndView("redirect:".concat(projectUrl.getWxchatOpenAuthorize())
//        .concat("/sell/wechat/qrAuthorize")
//        .concat("?returnUrl=")
//        .concat(projectUrl.getSell())
//        .concat("/sell/seller/login"));
    }


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtils.error(e.getCode(),e.getMessage());
    }






}
