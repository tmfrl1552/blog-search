package com.seulgi.services.search;

import com.seulgi.TrendKeywordService;
import com.seulgi.domain.search.Keyword;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.dto.search.SearchPopularRes;
import com.seulgi.dto.search.TrendKeywordDTO;
import com.seulgi.provider.search.SearchProvider;
import com.seulgi.repository.TrendKeywordRepository;
import com.seulgi.repository.search.SearchRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchProvider searchProvider;
    private final SearchRepository searchRepository;

    private final TrendKeywordService trendKeywordService;

    public SearchService(@Qualifier("KakaoSearchProvider") SearchProvider searchProvider,
                         SearchRepository searchRepository,
                         TrendKeywordService trendKeywordService) {
        this.searchProvider = searchProvider;
        this.searchRepository = searchRepository;
        this.trendKeywordService = trendKeywordService;
    }

    public SearchBlogRes searchBlog(SearchBlogReq req) {
//        incrementScore(req.getQuery());
        trendKeywordService.updateCountByKeyword(req.getQuery());

        return searchProvider.searchBlog(req);
    }

    @Cacheable(cacheNames = "popularKeywords", key = "#root.methodName", unless = "#result == null")
    public SearchPopularRes getPopularKeywords() {
        List<Keyword> popularKeywords = searchRepository.getPopularKeywords();

        return SearchPopularRes.builder()
                .keywords(popularKeywords)
                .build();
    }

    public List<TrendKeywordDTO> getTrendKeywords() {
        return trendKeywordService.getTop10TrendKeywordsLookAside();
    }

    /*private void incrementScore(String keyword) {
        // todo - keyword에 대해서 공백 제거 처리 추가 필요
        searchRepository.saveSearchKeyword(keyword);
    }*/
}
