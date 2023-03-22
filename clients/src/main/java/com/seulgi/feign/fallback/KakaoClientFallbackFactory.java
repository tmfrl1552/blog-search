package com.seulgi.feign.fallback;

import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.feign.OpenKakaoFeignClient;
import com.seulgi.feign.OpenNaverFeignClient;
import com.seulgi.dto.mapper.SearchDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoClientFallbackFactory implements FallbackFactory<OpenKakaoFeignClient> {

    private final OpenNaverFeignClient naverFeignClient;


    @Override
    public OpenKakaoFeignClient create(Throwable cause) {
        return req -> {
            log.warn("OpenKakaoFeignClient Fallback : {}", cause.getMessage());

            NaverSearchBlogRes response = naverFeignClient.searchBlog(
                    SearchDtoMapper.naverSearchBlogReqFrom(req));

            return SearchDtoMapper.kakaoSearchBlogResFrom(response);
        };
    }

}
