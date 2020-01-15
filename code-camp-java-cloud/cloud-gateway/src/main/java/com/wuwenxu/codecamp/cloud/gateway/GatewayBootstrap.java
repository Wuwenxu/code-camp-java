package com.wuwenxu.codecamp.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


/**
 *
 * @author  : zhangquan
 * @modified:
 * @version : 2019-05-21 14:18:08
 */
@SpringCloudApplication
public class GatewayBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(GatewayBootstrap.class,args);
  }
}
