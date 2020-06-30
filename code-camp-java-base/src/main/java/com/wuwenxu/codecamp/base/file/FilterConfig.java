package com.wuwenxu.codecamp.base.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 过滤器配置类
 *
 * @author KK
 * @Description
 * @createDate 2019/06/05 14:12
 */
//@Configuration
public class FilterConfig {

    /**
     * SSO服务地址
     */
    @Value("${koal.cas.server.ssologin}")
    private String ssologinUrl;
    /**
     * 应用系统访问地址
     */
    @Value("${koal.cas.server.serviceurl}")
    private String serviceurl;

    /**
     * 该过滤器用来进行登录验证
     */
    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addInitParameter("serviceurl", serviceurl);
        registration.addInitParameter("ssologinUrl", ssologinUrl);
        registration.setOrder(9);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
