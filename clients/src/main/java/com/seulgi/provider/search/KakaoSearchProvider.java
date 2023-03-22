package com.seulgi.provider.search;

import com.seulgi.dto.mapper.SearchDtoMapper;
import com.seulgi.dto.provider.kakao.KakaoSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.feign.OpenKakaoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Qualifier("KakaoSearchProvider")
@Component
@RequiredArgsConstructor
public class KakaoSearchProvider implements SearchProvider {

    final OpenKakaoFeignClient kakaoFeignClient;

    @Override
    public SearchBlogRes searchBlog(SearchBlogReq req) {
        req.setQuery(decodeQuery(req.getQuery()));

        KakaoSearchBlogRes response = kakaoFeignClient.searchBlog(req);

        return SearchDtoMapper.searchBlogResFrom(
                response, req.getPage(), req.getSize());
    }

    private String decodeQuery(String query) {
        return URLDecoder.decode(query, StandardCharsets.UTF_8);
    }
}
