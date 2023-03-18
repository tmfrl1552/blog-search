package com.seulgi.kakao;

import com.seulgi.dto.provider.kakao.SearchBlogRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenKakaoFeignClient", url = "https://dapi.kakao.com")
public interface
OpenKakaoFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v2/search/blog")
    SearchBlogRes searchBlog(@RequestHeader("Authorization") String token,
                             @RequestParam("query") String query,
                             @RequestParam("sort") String sort,
                             @RequestParam("page") int page,
                             @RequestParam("size") int size);
}
