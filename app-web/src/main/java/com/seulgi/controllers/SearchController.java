package com.seulgi.controllers;

import com.seulgi.search.domain.Search;
import com.seulgi.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seulgi/v1.0")
public class SearchController {

    final SearchService searchService;

    @GetMapping("/search/blog")
    public Search getSearch() {
        return searchService.getSearchDomain();
    }
}
