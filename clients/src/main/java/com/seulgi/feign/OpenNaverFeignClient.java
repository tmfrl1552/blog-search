package com.seulgi.feign;

import com.seulgi.config.feign.NaverFeignConfig;
import com.seulgi.dto.provider.naver.NaverSearchBlogReq;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.feign.fallback.NaverClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OpenNaverFeignClient", url = "${naver.open-api.url}",
        configuration = NaverFeignConfig.class, fallbackFactory = NaverClientFallbackFactory.class)
public interface OpenNaverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/search/blog.json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    NaverSearchBlogRes searchBlog(@SpringQueryMap NaverSearchBlogReq req);

}
