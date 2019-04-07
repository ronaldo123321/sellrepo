package com.anytec.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    public static void set(HttpServletResponse response,String name,String value,Integer maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }

    public static Cookie get(HttpServletRequest request,String name){
        Map<String, Cookie> stringCookieMap = readCookie(request);
        if(stringCookieMap.containsKey(name)){
            return stringCookieMap.get(name);
        }else {
            return null;

        }
    }


    public static Map<String,Cookie> readCookie(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
           for (Cookie cookie : cookies){
               cookieMap.put(cookie.getName(),cookie);
           }
        }
        return cookieMap;
    }

}
