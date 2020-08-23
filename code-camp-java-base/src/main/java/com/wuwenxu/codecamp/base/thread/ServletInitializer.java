package com.wuwenxu.codecamp.base.thread;

import com.wuwenxu.codecamp.base.filter.ConcurrencyApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ConcurrencyApplication.class);
	}

}
