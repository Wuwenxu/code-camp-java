package com.wuwenxu.codecamp.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 
 * @author  : Yong
 * @modified:
 * @version : 2019-01-18 14:18:08
 */
@SpringBootApplication()
@EnableEurekaServer()
public class EurekaBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(EurekaBootstrap.class,args);
  }
}
