package com.alejandro.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySources({
    @PropertySource("classpath:queries/product.properties"),
    @PropertySource("classpath:queries/review.properties"),
    @PropertySource("classpath:queries/utils.properties")
})
public class PropertiesProductConfig {
	
	@Bean()
	public PropertySourcesPlaceholderConfigurer loadProperties() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
