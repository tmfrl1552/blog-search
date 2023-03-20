package com.seulgi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-clients.properties")
@Configuration
public class ClientsConfig {
}
