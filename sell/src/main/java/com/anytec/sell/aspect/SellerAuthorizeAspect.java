package com.anytec.sell.aspect;

import com.anytec.sell.constant.RedisConstant;
import com.anytec.sell.exception.SellerAuthorizeException;
import com.anytec.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.anytec.sell.controller.Seller*.*(..))"+
            "&& !execution(public * com.anytec.sell.controller.SellerUserController.*(..))")
    public void verify(){

    }


    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, RedisConstant.TOKEN);
        if(cookie == null){
            log.warn("[登陆校验]查询不到token");
            throw new SellerAuthorizeException();
        }
        //去redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("[登陆校验]Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }

}
