package com.seulgi.services.search;

import com.seulgi.domain.search.Keyword;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.dto.search.SearchPopularRes;
import com.seulgi.enums.ResponseCode;
import com.seulgi.exceptions.SearchException;
import com.seulgi.provider.search.SearchProvider;
import com.seulgi.repository.search.PopularKeywordRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchProvider searchProvider;
    private final PopularKeywordRepository trendKeywordService;

    public SearchService(@Qualifier("KakaoSearchProvider") SearchProvider searchProvider,
                         PopularKeywordRepository trendKeywordService) {
        this.searchProvider = searchProvider;
        this.trendKeywordService = trendKeywordService;
    }

    public SearchBlogRes searchBlog(SearchBlogReq req) {
        checkParam(req.getQuery());

        SearchBlogRes response = searchProvider.searchBlog(req);

        trendKeywordService.updateCountByKeyword(req.getQuery());

        return response;
    }

    public SearchPopularRes getPopularKeywords() {

        List<Keyword> popularKeywords = trendKeywordService.getTop10PopularKeywords();

        return SearchPopularRes.builder()
                .keywords(popularKeywords)
                .build();
    }

    private void checkParam(String param) {
        if (Strings.isEmpty(param)) {
            throw new SearchException(ResponseCode.INVALID_PARAM);
        }
    }
}
