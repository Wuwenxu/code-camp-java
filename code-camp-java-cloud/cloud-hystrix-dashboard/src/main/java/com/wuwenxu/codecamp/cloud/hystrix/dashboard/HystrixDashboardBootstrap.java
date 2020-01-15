package com.wuwenxu.codecamp.cloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

 

/**
 * 
 * @author  : Yong
 * @modified:
 * @version : 2019-01-18 14:22:57
 */
@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
@EnableDiscoveryClient
public class HystrixDashboardBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(HystrixDashboardBootstrap.class,args);
  }
}
