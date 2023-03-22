package com.seulgi.provider.search;

import com.seulgi.domain.provider.naver.Item;
import com.seulgi.dto.provider.naver.NaverSearchBlogReq;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.enums.SearchSortType;
import com.seulgi.enums.provider.naver.NaverSortType;
import com.seulgi.feign.OpenNaverFeignClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

class NaverSearchProviderTest {

    OpenNaverFeignClient naverFeignClient = mock(OpenNaverFeignClient.class);

    NaverSearchProvider naverSearchProvider = new NaverSearchProvider(naverFeignClient);

    @Test
    @DisplayName("Naver search blog 성공 테스트")
    void searchBlogSuccessTest() {
        // given
        SearchBlogReq req = setSearchBlogReq();
        NaverSearchBlogReq naverReq = setNaverSearchBlogReq();
        NaverSearchBlogRes naverSearchBlogRes = setNaverSearchBlogRes();

        doReturn(naverSearchBlogRes).when(naverFeignClient).searchBlog(eq(naverReq));

        // when
        SearchBlogRes res = naverSearchProvider.searchBlog(req);

        // then
        verify(naverFeignClient).searchBlog(eq(naverReq));
        assertThat(res.getTotal()).isEqualTo(naverSearchBlogRes.getTotal());
        assertThat(res.getPage()).isEqualTo(naverReq.getStart());
        assertThat(res.getSize()).isEqualTo(naverSearchBlogRes.getDisplay());
        assertThat(res.getDocuments().get(0).getTitle())
                .isEqualTo(naverSearchBlogRes.getItems().get(0).getTitle());
        assertThat(res.getDocuments().get(0).getContents())
                .isEqualTo(naverSearchBlogRes.getItems().get(0).getDescription());
        assertThat(res.getDocuments().get(0).getUrl())
                .isEqualTo(naverSearchBlogRes.getItems().get(0).getLink());
        assertThat(res.getDocuments().get(0).getDatetime())
                .isEqualTo(naverSearchBlogRes.getItems().get(0).getPostdate());
        assertThat(res.getDocuments().get(0).getBlogname())
                .isEqualTo(naverSearchBlogRes.getItems().get(0).getBloggername());
        assertThat(res.getDocuments().get(0).getThumbnail()).isNull();
    }

    static SearchBlogReq setSearchBlogReq() {
        return SearchBlogReq.builder()
                .query("kakao")
                .sort(SearchSortType.ACCURACY)
                .page(1)
                .size(10)
                .build();
    }

    static NaverSearchBlogReq setNaverSearchBlogReq() {
        return NaverSearchBlogReq.builder()
                .query("kakao")
                .sort(NaverSortType.SIM.getName())
                .start(1)
                .display(10)
                .build();
    }

    static NaverSearchBlogRes setNaverSearchBlogRes() {
        NaverSearchBlogRes result = new NaverSearchBlogRes();

        Item item = new Item();
        item.setTitle("kakao");
        item.setDescription("kakao-bank");
        item.setLink("https://kakao-bank.com");
        item.setPostdate(new Date(System.currentTimeMillis()));
        item.setBloggername("seulgi");


        result.setTotal(20);
        result.setStart(1);
        result.setDisplay(10);
        result.setItems(Collections.singletonList(item));

        return result;
    }

}