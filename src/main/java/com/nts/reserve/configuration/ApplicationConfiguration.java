package com.nts.reserve.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:/property/application.properties" })
@ComponentScan(basePackages = { "com.nts.reserve"})
@Import({ DBConfiguration.class, ContextSqlMapper.class })
public class ApplicationConfiguration {

}
