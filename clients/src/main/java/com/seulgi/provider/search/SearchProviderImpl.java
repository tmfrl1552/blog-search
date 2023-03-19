package com.seulgi.provider.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seulgi.domain.search.Document;
import com.seulgi.domain.search.Meta;
import com.seulgi.dto.provider.kakao.KakaoSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.kakao.OpenKakaoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchProviderImpl implements SearchProvider {

    final OpenKakaoFeignClient kakaoFeignClient;
    final ObjectMapper objectMapper;

    @Value("${kakao.open-api.token}")
    private String token;

    @Override
    public SearchBlogRes searchBlog(SearchBlogReq req) {
        SearchBlogRes result;

        try {
            KakaoSearchBlogRes response = kakaoFeignClient.searchBlog(
                    token, req.getQuery(), req.getSort().getName(), req.getPage(), req.getSize());

            // todo - [공통] 블로그 검색 dto로 변환하여 응답 주기
            result = SearchBlogRes.builder()
                    .meta(Meta.builder()
                            .total(response.getMeta().getPageableCount())
                            .page(req.getPage())
                            .size(req.getSize())
                            .isEnd(response.getMeta().isEnd())
                            .build())
                    .documents(response.getDocuments().stream()
                            .map(d -> objectMapper.convertValue(d, Document.class))
                            .collect(Collectors.toList()))
                    .build();
        } catch (Exception e) {
            log.error("searchBlog error by Kakao Search api.", e);
            result = searchBlogByOther(req);
        }

        return result;
    }

    private SearchBlogRes searchBlogByOther(SearchBlogReq req) {
        // kakao 장애를 대비하여 naver search api 호출하여 가져오는 부분 추가 필요
        return null;
    }
}
