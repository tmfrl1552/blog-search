package com.seulgi.config.feign;

import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class NaverFeignConfig {


    @Bean
    public RequestInterceptor naverRequestInterceptor(
            @Value("${naver.open-api.client.id}") String NAVER_CLIENT_ID,
            @Value("${naver.open-api.client.secret}") String NAVER_CLIENT_SECRET) {

        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", NAVER_CLIENT_ID);
            requestTemplate.header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);
        };
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
    }

    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(
                5, TimeUnit.SECONDS,
                10, TimeUnit.SECONDS,
                true
        );
    }

}
