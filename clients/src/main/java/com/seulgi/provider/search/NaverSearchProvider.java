package com.seulgi.provider.search;

import com.seulgi.dto.mapper.SearchDtoMapper;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.feign.OpenNaverFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Slf4j
@Qualifier("NaverSearchProvider")
@Component
@RequiredArgsConstructor
public class NaverSearchProvider implements SearchProvider {

    final OpenNaverFeignClient naverFeignClient;

    @Override
    public SearchBlogRes searchBlog(SearchBlogReq req) {
        NaverSearchBlogRes response = naverFeignClient.searchBlog(
                SearchDtoMapper.naverSearchBlogReqFrom(req));

        return SearchDtoMapper.searchBlogResFrom(response);
    }

}
