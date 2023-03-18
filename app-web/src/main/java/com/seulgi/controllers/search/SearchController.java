package com.seulgi.controllers.search;

import com.seulgi.domain.search.Search;
import com.seulgi.dto.search.SearchBlogsReq;
import com.seulgi.enums.SearchSortType;
import com.seulgi.services.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seulgi/v1.0")
public class SearchController {

    final SearchService searchService;

    @GetMapping("/search/blog")
    public Search searchBlogs(@RequestParam String query,
                              @RequestParam(defaultValue = "ACCURACY", required = false) SearchSortType sort,
                              @RequestParam(defaultValue = "1", required = false) int page,
                              @RequestParam(defaultValue = "10", required = false) int size) {
        return searchService.searchBlogs(
                SearchBlogsReq.builder()
                        .query(query)
                        .sort(sort)
                        .page(page)
                        .size(size)
                        .build());
    }
}
