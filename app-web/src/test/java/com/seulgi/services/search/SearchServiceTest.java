package com.seulgi.services.search;

import com.seulgi.domain.search.Document;
import com.seulgi.domain.search.Keyword;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.dto.search.SearchPopularRes;
import com.seulgi.enums.SearchSortType;
import com.seulgi.provider.search.SearchProvider;
import com.seulgi.repository.search.PopularKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class SearchServiceTest {

    SearchProvider searchProvider = mock(SearchProvider.class);
    PopularKeywordRepository popularKeywordRepository = mock(PopularKeywordRepository.class);

    SearchService searchService = new SearchService(searchProvider, popularKeywordRepository);

    @Test
    @DisplayName("블로그 검색 성공 테스트")
    public void searchBlogSuccessTest() {
        // given
        SearchBlogReq reqDto = setSearchBlogMockReq();
        Date date = new Date(System.currentTimeMillis());

        SearchBlogRes resDto = setSearchBlogMockRes();

        doReturn(resDto).when(searchProvider).searchBlog(eq(reqDto));

        // when
        SearchBlogRes response = searchService.searchBlog(reqDto);

        // then
        verify(searchProvider).searchBlog(eq(reqDto));
        verify(popularKeywordRepository).updateScoreByKeyword(eq(reqDto.getQuery()));
        assertThat(response.getPage()).isEqualTo(resDto.getPage());
    }

    @Test
    @DisplayName("블로그 검색 실패 테스트 - 필수 파라미터가 empty인 경우")
    public void searchBlogFailTest() {
        // given
        SearchBlogReq req = setSearchBlogMockReq();
        req.setQuery("");

        // when
        Throwable throwable = catchThrowable(() -> searchService.searchBlog(req));

        // then
        verify(searchProvider, never()).searchBlog(any());
        verify(popularKeywordRepository, never()).updateScoreByKeyword(any());
        assertThat(throwable.getMessage()).isEqualTo("Invalid Param");

    }

    @Test
    @DisplayName("인기 검색어 조회 성공 테스트")
    public void getPopularKeywordsSuccessTest() {
        // given
        List<Keyword> keywords = setPopularKeywords();

        doReturn(keywords)
                .when(popularKeywordRepository)
                .getTop10PopularKeywords();

        // when
        SearchPopularRes response = searchService.getPopularKeywords();

        // then
        assertThat(response.getKeywords().get(0).getKeyword())
                .isEqualTo(keywords.get(0).getKeyword());
        assertThat(response.getKeywords().get(0).getScore())
                .isEqualTo(keywords.get(0).getScore());
        assertThat(response.getKeywords().get(1).getKeyword())
                .isEqualTo(keywords.get(1).getKeyword());
        assertThat(response.getKeywords().get(1).getScore())
                .isEqualTo(keywords.get(1).getScore());
    }

    static SearchBlogReq setSearchBlogMockReq() {
        return SearchBlogReq.builder()
                .query("합격")
                .page(1)
                .size(5)
                .sort(SearchSortType.ACCURACY)
                .build();
    }

    static SearchBlogRes setSearchBlogMockRes() {
        Date date = new Date(System.currentTimeMillis());

        return SearchBlogRes.builder()
                .total(20)
                .page(1)
                .size(5)
                .isEnd(false)
                .documents(Collections.singletonList(Document.builder()
                        .title("합격 기원")
                        .contents("제곧내")
                        .thumbnail("https://seulgi/kakao.png")
                        .blogname("seulgi")
                        .url("https://seulgi.com")
                        .datetime(date)

                        .build()))
                .build();
    }

    static List<Keyword> setPopularKeywords() {
        List<Keyword> keywords = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            keywords.add(Keyword.of(String.format("seulgi_%s", i), (i *5L)));
        }

        return keywords;
    }

}