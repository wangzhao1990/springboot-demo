package com.zhao.demo.common.utils.http;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * HttpPoolProperties
 *
 * @author wangzhao
 * @create 2019/4/10
 */
@Component
@ConfigurationProperties(prefix = "http-pool")
@Data
public class HttpPoolProperties {
    private Integer maxTotal;
    private Integer defaultMaxPerRoute;
    private Integer connectTimeout;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;
    private Integer validateAfterInactivity;
}
