package com.anytec.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "projecturl")
public class ProjectUrl {
    /**
     * 微信公众平台授权url
     */
    public String wxchatMpAuthorize;

    /**
     * 微信开放平台授权url
     */
    public String wxchatOpenAuthorize;

    /**
     * 点餐系统
     */
    public String sell;
}
