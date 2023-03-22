package com.seulgi.provider.search;

import com.seulgi.domain.search.Document;
import com.seulgi.dto.provider.naver.NaverSearchBlogReq;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.enums.provider.naver.NaverSortType;
import com.seulgi.feign.OpenNaverFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Override
    public SearchBlogRes searchBlog(SearchBlogReq req) {
        String encodeQuery = URLEncoder.encode(req.getQuery(), StandardCharsets.UTF_8);
        NaverSortType sortType = NaverSortType.getSortType(req.getSort());

        NaverSearchBlogReq naverSearchBlogReq = NaverSearchBlogReq.builder()
                .query(encodeQuery)
                .start(req.getPage())
                .display(req.getSize())
                .sort(sortType.getName())
                .build();

        NaverSearchBlogRes response = naverFeignClient.searchBlog(naverSearchBlogReq);

        return SearchBlogRes.builder()
                .total(response.getTotal())
                .page(response.getStart())
                .size(response.getDisplay())
                .isEnd(isEnd(response.getTotal(), response.getStart(), response.getDisplay()))
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
    }

    private boolean isEnd(int total, int page, int size) {
        return total <= (page * size);
    }

}
