package com.seulgi.services;

import com.seulgi.search.domain.Search;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    public Search getSearchDomain() {
        Search search = new Search();
        search.setTitle("슬기의 글 제목");
        search.setContent("슬기의 글 본문");

        return search;
    }
}
