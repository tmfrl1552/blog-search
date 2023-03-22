package com.seulgi.feign.fallback;

import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.feign.OpenNaverFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NaverClientFallbackFactory implements FallbackFactory<OpenNaverFeignClient> {

    @Override
    public OpenNaverFeignClient create(Throwable cause) {
        return req -> {
            log.warn("OpenNaverFeignClient Fallback : {}", cause.getMessage());
            return new NaverSearchBlogRes();
        };
    }
}
