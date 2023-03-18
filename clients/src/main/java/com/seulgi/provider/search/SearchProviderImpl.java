package com.seulgi.provider.search;

import com.seulgi.dto.provider.kakao.SearchBlogRes;
import com.seulgi.dto.search.SearchBlogsReq;
import com.seulgi.kakao.OpenKakaoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchProviderImpl implements SearchProvider {

    final OpenKakaoFeignClient kakaoFeignClient;

    @Value("${kakao.open-api.token}")
    private String token;

    @Override
    public void searchBlog(SearchBlogsReq req) {
        // kakao 검색 api 호출해서 응답 값 조회하기


        SearchBlogRes response = kakaoFeignClient.searchBlog(
                token, req.getQuery(), req.getSort().getName(), req.getPage(), req.getSize());


        // 응답 실패 했을 때 예외처리 진행하기
    }
}
