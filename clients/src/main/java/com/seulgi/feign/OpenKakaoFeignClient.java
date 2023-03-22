package com.seulgi.feign;

import com.seulgi.config.feign.KakaoFeignConfig;
import com.seulgi.dto.provider.kakao.KakaoSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.feign.fallback.KakaoClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OpenKakaoFeignClient", url = "${kakao.open-api.url}",
        configuration = KakaoFeignConfig.class, fallbackFactory = KakaoClientFallbackFactory.class)
public interface OpenKakaoFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v2/search/blog",
            produces = MediaType.APPLICATION_JSON_VALUE)
    KakaoSearchBlogRes searchBlog(@SpringQueryMap SearchBlogReq req);
}
