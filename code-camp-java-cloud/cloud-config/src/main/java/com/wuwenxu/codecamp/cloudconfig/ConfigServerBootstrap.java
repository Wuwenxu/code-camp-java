package com.wuwenxu.codecamp.cloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * 
 * @author  : Yong
 * @modified:
 * @version : 2019-01-18 14:32:58
 */
@SpringBootApplication()
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(ConfigServerBootstrap.class,args);
  }
}
