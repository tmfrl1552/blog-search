package com.seulgi.feign;

import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenNaverFeignClient", url = "${naver.open-api.url}")
public interface OpenNaverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/search/blog.json")
    NaverSearchBlogRes searchBlog(@RequestHeader("X-Naver-Client-Id") String clientId,
                                  @RequestHeader("X-Naver-Client-Secret") String clientSecret,
                                  @RequestParam("query") String query,
                                  @RequestParam("start") int start,
                                  @RequestParam("display") int display,
                                  @RequestParam("sort") String sort);

}
