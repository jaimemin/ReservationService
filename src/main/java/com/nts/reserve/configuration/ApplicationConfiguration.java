package com.nts.reserve.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:application.properties" })
@ComponentScan(basePackages = { "com.nts.reserve.dao", "com.nts.reserve.service"})
@Import({ DBConfiguration.class })
public class ApplicationConfiguration {

}
