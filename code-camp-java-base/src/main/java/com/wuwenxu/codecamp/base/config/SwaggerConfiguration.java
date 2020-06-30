package com.wuwenxu.codecamp.base.config;


import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhangdx
 * @Date: 2019/10/28 21:13
 * @Copyright: Fujian Linewell Software Co., Ltd. All rights reserved.
 */

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    /**
     * 指定扫描的包
     */
    private static String[] SWAGGER_SCAN_BASE_PACKAGE;
    public static final String VERSION = "1.0.0";

    @Value("${swagger.scan.base.path:com.aliyun.microservice.sxzl.rest.sys}")
    public void setSwaggerScanBasePackage(String scanBasePackage) {
        SwaggerConfiguration.SWAGGER_SCAN_BASE_PACKAGE = StringUtils.tokenizeToStringArray(scanBasePackage, BeanDefinitionParserDelegate.MULTI_VALUE_ATTRIBUTE_DELIMITERS);
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .description("This is to show api description")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version(VERSION)
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(SwaggerConfiguration.createMultiScan())
                .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

    /**
     * 创建扫描多个controller路径
     *
     * @return
     */
    private static Predicate createMultiScan() {
        ScanMultiBasePathPredicate pathPredicate = new ScanMultiBasePathPredicate();
        for (String path : SWAGGER_SCAN_BASE_PACKAGE) {
            pathPredicate.addPredicate(RequestHandlerSelectors.basePackage(path));
        }
        return pathPredicate;
    }

    /**
     * Decorator Pattern
     * 扫描多个controller路径进行匹配,满足其一及可
     * 后续可以改成正则匹配,就无需配置多个路径了
     */
    public static class ScanMultiBasePathPredicate implements Predicate<springfox.documentation.RequestHandler> {

        private List<Predicate> predicateList = new ArrayList();

        @Override
        public boolean apply(RequestHandler input) {
            for (Predicate p : predicateList) {
                if (p.apply(input)) {
                    return true;
                }
            }
            return false;
        }

        protected void addPredicate(Predicate predicates) {
            predicateList.add(predicates);
        }

    }
}
