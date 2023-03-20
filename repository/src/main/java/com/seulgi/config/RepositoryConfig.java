package com.seulgi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-repository.properties")
@Configuration
public class RepositoryConfig {
}
