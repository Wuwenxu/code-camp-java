package com.wuwenxu.codecamp.cloudconfig.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author  : Yong
 * @modified:
 * @version : 2019-01-18 14:32:52
 */
@Configuration
@ConfigurationProperties(value="config")
public class ServerAutoConfig {
}
