package com.seulgi.provider.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seulgi.domain.search.Document;
import com.seulgi.dto.provider.kakao.KakaoSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.feign.OpenKakaoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Qualifier("KakaoSearchProvider")
@Component
@RequiredArgsConstructor
public class KakaoSearchProvider implements SearchProvider {

    final OpenKakaoFeignClient kakaoFeignClient;
    final ObjectMapper objectMapper;

    @Override
    public SearchBlogRes searchBlog(SearchBlogReq req) {
        KakaoSearchBlogRes response = kakaoFeignClient.searchBlog(req);

        return SearchBlogRes.builder()
                .total(response.getMeta().getPageableCount())
                .page(req.getPage())
                .size(req.getSize())
                .isEnd(response.getMeta().isEnd())
                .documents(response.getDocuments().stream()
                        .map(d -> objectMapper.convertValue(d, Document.class))
                        .collect(Collectors.toList()))
                .build();
    }
}
