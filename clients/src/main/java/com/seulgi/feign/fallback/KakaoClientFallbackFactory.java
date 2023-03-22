package com.seulgi.feign.fallback;

import com.seulgi.domain.provider.kakao.KakaoDocument;
import com.seulgi.domain.provider.kakao.KakaoMeta;
import com.seulgi.dto.provider.kakao.KakaoSearchBlogRes;
import com.seulgi.dto.provider.naver.NaverSearchBlogReq;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.enums.provider.naver.NaverSortType;
import com.seulgi.feign.OpenKakaoFeignClient;
import com.seulgi.feign.OpenNaverFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoClientFallbackFactory implements FallbackFactory<OpenKakaoFeignClient> {

    private final OpenNaverFeignClient naverFeignClient;


    @Override
    public OpenKakaoFeignClient create(Throwable cause) {
        return req -> {
            log.warn("OpenKakaoFeignClient Fallback : {}", cause.getMessage());

            // todo - sortType 변경 및, query encode
            String encodeQuery = URLEncoder.encode(req.getQuery(), StandardCharsets.UTF_8);
            NaverSortType sortType = NaverSortType.getSortType(req.getSort());

            NaverSearchBlogReq naverSearchBlogReq = NaverSearchBlogReq.builder()
                    .query(encodeQuery)
                    .start(req.getPage())
                    .display(req.getSize())
                    .sort(sortType.getName())
                    .build();

            NaverSearchBlogRes response = naverFeignClient.searchBlog(naverSearchBlogReq);

            KakaoSearchBlogRes result = KakaoSearchBlogRes.builder().build();

            result.setMeta(KakaoMeta.builder()
                            .totalCount(response.getTotal())
                            .pageableCount(response.getTotal())
                            .isEnd(isEnd(response.getTotal(), req.getPage(), req.getSize()))
                    .build());
            result.setDocuments(response.getItems().stream()
                    .map(i -> KakaoDocument.builder()
                            .title(i.getTitle())
                            .contents(i.getDescription())
                            .url(i.getLink())
                            .blogname(i.getBloggername())
                            .datetime(i.getPostdate())
                            .build())
                    .collect(Collectors.toList()));


            return result;
        };
    }

    private boolean isEnd(int total, int page, int size) {
        return total <= (page * size);
    }

}
