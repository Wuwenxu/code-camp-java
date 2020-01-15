package com.wuwenxu.codecamp.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;



/**
 *
 * @author  : lihuiming
 * @modified:
 * @version : 2019-04-10 16:03
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(ZipkinBootstrap.class,args);
  }
}
