package com.zhao.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = {"com.zhao.demo"})
public class DemoApiApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        LOGGER.info("SpringApplicationBuilder.configure()...");
        return builder.sources(DemoApiApplication.class);
    }
}
