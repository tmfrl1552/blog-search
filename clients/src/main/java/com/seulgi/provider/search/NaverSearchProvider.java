package com.seulgi.provider.search;

import com.seulgi.domain.search.Document;
import com.seulgi.domain.search.Meta;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.feign.OpenNaverFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@Slf4j
@Qualifier("NaverSearchProvider")
@Component
@RequiredArgsConstructor
public class NaverSearchProvider implements SearchProvider {

    final OpenNaverFeignClient naverFeignClient;

    @Value("${naver.open-api.client.id}")
    private String clientId;

    @Value("${naver.open-api.client.secret}")
    private String clientSecret;


    @Override
    public SearchBlogRes searchBlog(SearchBlogReq req) {
        SearchBlogRes result = null;

        try {
            // todo - sort type 어떻게 해줄건지,, > 공통화 해줄건지?
            String encodeQuery = URLEncoder.encode(req.getQuery(), StandardCharsets.UTF_8);

            NaverSearchBlogRes response = naverFeignClient.searchBlog(
                    clientId, clientSecret, encodeQuery, req.getPage(), req.getSize(), "sim");

            result = SearchBlogRes.builder()
                    .meta(Meta.builder()
                            .total(response.getTotal())
                            .page(response.getStart())
                            .size(response.getDisplay())
                            .isEnd(isEnd(response.getTotal(),
                                    response.getStart(),
                                    response.getDisplay()))
                            .build())
                    .documents(response.getItems().stream()
                            .map(i -> Document.builder()
                                    .title(i.getTitle())
                                    .contents(i.getDescription())
                                    .url(i.getLink())
                                    .blogname(i.getBloggername())
                                    .datetime(i.getPostdate())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();
        } catch (Exception e) {
            log.error("[SEARCH PROVIDER] searchBlog error by Naver Search api.", e);
        }

        return result;
    }

    private boolean isEnd(int total, int page, int size) {
        return total <= (page * size);
    }
}