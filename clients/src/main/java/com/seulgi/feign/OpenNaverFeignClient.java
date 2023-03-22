package com.seulgi.feign;

import com.seulgi.config.feign.NaverFeignConfig;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenNaverFeignClient", url = "${naver.open-api.url}", configuration = NaverFeignConfig.class)
public interface OpenNaverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/search/blog.json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    NaverSearchBlogRes searchBlog(@RequestHeader("X-Naver-Client-Id") String clientId,
                                  @RequestHeader("X-Naver-Client-Secret") String clientSecret,
                                  @RequestParam("query") String query,
                                  @RequestParam("start") int start,
                                  @RequestParam("display") int display,
                                  @RequestParam("sort") String sort);

}
