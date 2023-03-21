package com.seulgi.services.search;

import com.seulgi.PopularKeywordService;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.dto.search.SearchPopularRes;
import com.seulgi.domain.search.Keyword;
import com.seulgi.provider.search.SearchProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchProvider searchProvider;
    private final PopularKeywordService trendKeywordService;

    public SearchService(@Qualifier("KakaoSearchProvider") SearchProvider searchProvider,
                         PopularKeywordService trendKeywordService) {
        this.searchProvider = searchProvider;
        this.trendKeywordService = trendKeywordService;
    }

    public SearchBlogRes searchBlog(SearchBlogReq req) {
        SearchBlogRes response = searchProvider.searchBlog(req);

        trendKeywordService.updateCountByKeyword(req.getQuery());

        return response;
    }

    public SearchPopularRes getPopularKeywords() {

        List<Keyword> popularKeywords = trendKeywordService.getTop10TrendKeywordsLookAside();

        return SearchPopularRes.builder()
                .keywords(popularKeywords)
                .build();
    }
}
