package com.seulgi.services.search;

import com.seulgi.domain.search.Keyword;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.dto.search.SearchPopularRes;
import com.seulgi.provider.search.SearchProvider;
import com.seulgi.repository.search.SearchRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchProvider searchProvider;
    private final SearchRepository searchRepository;

    public SearchService(@Qualifier("KakaoSearchProvider") SearchProvider searchProvider,
                         SearchRepository searchRepository) {
        this.searchProvider = searchProvider;
        this.searchRepository = searchRepository;
    }

    public SearchBlogRes searchBlog(SearchBlogReq req) {
        // todo - 여기서 뭐 더 추가로 확인해줘야 하는거나 예외처리
        //  해줘야 되는 부분 없을지 생각 필요
        incrementScore(req.getQuery());

        return searchProvider.searchBlog(req);
    }

    @Cacheable(cacheNames = "popularKeywords", key = "#root.methodName", unless = "#result == null")
    public SearchPopularRes getPopularKeywords() {
        List<Keyword> popularKeywords = searchRepository.getPopularKeywords();

        return SearchPopularRes.builder()
                .keywords(popularKeywords)
                .build();
    }

    private void incrementScore(String keyword) {
        // todo - keyword에 대해서 공백 제거 처리 추가 필요
        searchRepository.saveSearchKeyword(keyword);
    }
}
