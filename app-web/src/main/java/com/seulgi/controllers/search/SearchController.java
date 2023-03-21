package com.seulgi.controllers.search;

import com.seulgi.aop.response.ApiResponse;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.dto.search.SearchPopularRes;
import com.seulgi.domain.search.Keyword;
import com.seulgi.enums.SearchSortType;
import com.seulgi.services.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/search")
@ApiResponse
public class SearchController {

    final SearchService searchService;

    @GetMapping("/blog")
    public SearchBlogRes searchBlog(@RequestParam String query,
                                    @RequestParam(defaultValue = "ACCURACY", required = false) SearchSortType sort,
                                    @RequestParam(defaultValue = "1", required = false) int page,
                                    @RequestParam(defaultValue = "10", required = false) int size) {
        return searchService.searchBlog(
                SearchBlogReq.builder()
                        .query(query)
                        .sort(sort)
                        .page(page)
                        .size(size)
                        .build());
    }

    @GetMapping("/popular/keyword")
    public SearchPopularRes getPopularKeywords() {
        return searchService.getPopularKeywords();
    }
}
