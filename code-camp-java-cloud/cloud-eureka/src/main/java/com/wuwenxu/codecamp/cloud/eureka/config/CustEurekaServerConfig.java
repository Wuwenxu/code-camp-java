package com.wuwenxu.codecamp.cloud.eureka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 
 * @author  : Yong
 * @modified:
 * @version : 2019-01-18 14:18:03
 */
@Configuration
@ConfigurationProperties(value="eureka")
@Data
public class CustEurekaServerConfig {
  private String environment;
  private String datacenter;
}
