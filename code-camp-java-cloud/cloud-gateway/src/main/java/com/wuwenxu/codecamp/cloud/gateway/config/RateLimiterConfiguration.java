package com.wuwenxu.codecamp.cloud.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author zhangquan
 * @date 2019/5/21
 * 路由限流配置
 */
@Configuration
public class RateLimiterConfiguration {
  @Bean(value = "remoteAddrKeyResolver")
  public KeyResolver remoteAddrKeyResolver() {
    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
  }
}
