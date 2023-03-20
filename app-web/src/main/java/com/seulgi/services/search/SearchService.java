package com.seulgi.services.search;

import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.dto.search.SearchPopularRes;
import com.seulgi.provider.search.SearchProvider;
import com.seulgi.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchProvider searchProvider;
    private final SearchRepository searchRepository;

    public SearchBlogRes searchBlog(SearchBlogReq req) {
        // todo - 여기서 뭐 더 추가로 확인해줘야 하는거나 예외처리해줘야 되는 부분 없을지 생각
        incrementScore(req.getQuery());

        return searchProvider.searchBlog(req);
    }

    public SearchPopularRes getPopularKeywords() {
        // todo - 인기 검색어 목록 구현을 어떻게 해야 할까?

        return null;
    }

    private void incrementScore(String keyword) {
        // todo - keyword에 대해서 공백 제거 처리
        searchRepository.saveSearchKeyword(keyword);
    }
}
