package com.seulgi.provider.search;

import com.seulgi.domain.provider.kakao.KakaoDocument;
import com.seulgi.domain.provider.kakao.KakaoMeta;
import com.seulgi.dto.provider.kakao.KakaoSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.enums.SearchSortType;
import com.seulgi.feign.OpenKakaoFeignClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

class KakaoSearchProviderTest {

    OpenKakaoFeignClient kakaoFeignClient = mock(OpenKakaoFeignClient.class);

    KakaoSearchProvider kakaoSearchProvider = new KakaoSearchProvider(kakaoFeignClient);

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("searchBlog 성공 테스트")
    void searchBlogSuccessTest() {
        // given
        SearchBlogReq req = setSearchBlogReq();
        KakaoSearchBlogRes kakaoSearchBlogRes = setKakaoSearchBlogRes();

        doReturn(kakaoSearchBlogRes).when(kakaoFeignClient).searchBlog(eq(req));

        // when
        SearchBlogRes res = kakaoSearchProvider.searchBlog(req);

        // then
        verify(kakaoFeignClient).searchBlog(eq(req));
        assertThat(res).isNotNull();
        assertThat(res.getTotal()).isEqualTo(kakaoSearchBlogRes.getMeta().getPageableCount());
        assertThat(res.getPage()).isEqualTo(req.getPage());
        assertThat(res.getSize()).isEqualTo(req.getSize());
        assertThat(res.getDocuments().get(0).getTitle())
                .isEqualTo(kakaoSearchBlogRes.getDocuments().get(0).getTitle());
        assertThat(res.getDocuments().get(0).getContents())
                .isEqualTo(kakaoSearchBlogRes.getDocuments().get(0).getContents());
        assertThat(res.getDocuments().get(0).getBlogname())
                .isEqualTo(kakaoSearchBlogRes.getDocuments().get(0).getBlogname());
        assertThat(res.getDocuments().get(0).getUrl())
                .isEqualTo(kakaoSearchBlogRes.getDocuments().get(0).getUrl());
        assertThat(res.getDocuments().get(0).getThumbnail())
                .isEqualTo(kakaoSearchBlogRes.getDocuments().get(0).getThumbnail());
        assertThat(res.getDocuments().get(0).getDatetime())
                .isEqualTo(kakaoSearchBlogRes.getDocuments().get(0).getDatetime());
        assertThat(res.getDocuments().get(0).getBlogname())
                .isEqualTo(kakaoSearchBlogRes.getDocuments().get(0).getBlogname());
    }

    static SearchBlogReq setSearchBlogReq() {
        return SearchBlogReq.builder()
                .query("kakao")
                .sort(SearchSortType.ACCURACY)
                .page(1)
                .size(10)
                .build();
    }

    static KakaoSearchBlogRes setKakaoSearchBlogRes() {
        return KakaoSearchBlogRes.builder()
                .meta(KakaoMeta.builder()
                        .totalCount(100)
                        .pageableCount(80)
                        .isEnd(false)
                        .build())
                .documents(Collections.singletonList(
                        KakaoDocument.builder()
                                .title("kakao")
                                .contents("kakao-bank")
                                .url("https://kakao-bank.com")
                                .thumbnail("https://kakao-bank.com")
                                .datetime(new Date(System.currentTimeMillis()))
                                .blogname("seulgi")
                                .build()))
                .build();
    }

}