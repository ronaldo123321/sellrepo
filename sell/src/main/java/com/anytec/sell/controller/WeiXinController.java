package com.anytec.sell.controller;

import com.anytec.sell.VO.ResultVO;
import com.anytec.sell.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {

    /**
     * 手工方式获取OPENID的步骤：
     * 1.设置域名
     * 2.获取code
     * 3.换取access_token
     * @param code
     */
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        //获取的数据：access_token   expire    refresh_token    openid
        log.info("response={}",response);
    }

    @GetMapping("/auth1")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("进入auth1方法");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String echostrVal = "";
        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
           log.info("key:"+entry.getKey());
           log.info("value:"+entry.getValue()[0]);
           if(entry.getKey().equalsIgnoreCase("echostr")){
               echostrVal = entry.getValue()[0];
           }
        }

      //  return true;
    }

    @GetMapping("/pig")
    public ResultVO resultVO(){
        Map<String,String> map = new HashMap<>();
        map.put("to see here:","I forget about everything else,\n" +""+
                "Listening to your voice.\n" + ""+
                "Only a glimpse of your grin,\n" + ""+
                " aVague hint at your smile,\n" + ""+
                "Evokes my heart's desires:\n" + ""+
                "Your hand in mine,\n" +""+
                "Our lives spent together.\n" + ""+
                "Us can mean you and me, \n" + ""+
                "let's do it forever."

               );
        return ResultVOUtils.success(map);
    }
}
