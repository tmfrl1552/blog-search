package com.seulgi.services.search;

import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.provider.search.SearchProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchProvider searchProvider;

    public SearchBlogRes searchBlog(SearchBlogReq req) {
        // todo - 여기서 뭐 더 추가로 확인해줘야 하는거나 예외처리해줘야 되는 부분 없을지 생각해보기d
        return searchProvider.searchBlog(req);
    }

    // todo - 인기 검색어 목록 조회 서비스 구현
    public void getPopularKeywords() {

    }
}
