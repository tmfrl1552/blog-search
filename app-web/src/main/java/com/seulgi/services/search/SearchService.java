package com.seulgi.services.search;

import com.seulgi.domain.search.Search;
import com.seulgi.dto.search.SearchBlogsReq;
import com.seulgi.provider.search.SearchProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchProvider searchProvider;

    public Search searchBlog(SearchBlogsReq req) {
        // todo - api 통신하는 부분을 어디에다가 만들어야 할까?
        searchProvider.searchBlog(req);

        // test code
        Search search = new Search();
        search.setTitle("슬기의 글 제목");
        search.setContent("슬기의 글 본문");

        return search;
    }

    // todo - 인기 검색어 목록 조회 서비스 구현
    public void getPopularKeywords() {

    }
}
