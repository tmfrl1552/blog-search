package com.seulgi.controllers.search;

import com.seulgi.dto.provider.kakao.KaKaoSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
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

    // todo - query 안들어 왔을 때 예외처리 해주면 좋을
    @GetMapping("/search/blog")
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
}
