package com.seulgi.config.feign;

import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class KakaoFeignConfig {

    @Bean
    public RequestInterceptor kakaoRequestInterceptor(
            @Value("${kakao.open-api.token}") String KAKAO_API_KEY) {
        return requestTemplate -> requestTemplate.header("Authorization", KAKAO_API_KEY);
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
