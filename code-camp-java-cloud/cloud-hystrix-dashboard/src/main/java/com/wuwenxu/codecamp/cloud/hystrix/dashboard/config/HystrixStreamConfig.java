package com.wuwenxu.codecamp.cloud.hystrix.dashboard.config;

import javax.servlet.Servlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/**
 * 
 * @author  : Yong
 * @modified:
 * @version : 2019-01-18 14:23:02
 */
@Configuration
@ConditionalOnProperty(value="hystrix.stream.enabled",havingValue="true", matchIfMissing=false)
public class HystrixStreamConfig {
  @Bean
  public ServletRegistrationBean<Servlet> getHystrixStreamServlet() {
    ServletRegistrationBean<Servlet> regBean;
    HystrixMetricsStreamServlet      streamServlet;
    streamServlet = new HystrixMetricsStreamServlet();
    regBean = new ServletRegistrationBean<Servlet>(streamServlet);
    regBean.setLoadOnStartup(1);
    regBean.addUrlMappings("/hystrix.stream");
    regBean.setName("HystrixMetricsStreamServlet");    
    return regBean;
  }
}
