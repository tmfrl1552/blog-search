package com.seulgi.dto.mapper;

import com.seulgi.domain.provider.kakao.KakaoDocument;
import com.seulgi.domain.provider.kakao.KakaoMeta;
import com.seulgi.domain.search.Document;
import com.seulgi.dto.provider.kakao.KakaoSearchBlogRes;
import com.seulgi.dto.provider.naver.NaverSearchBlogReq;
import com.seulgi.dto.provider.naver.NaverSearchBlogRes;
import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;
import com.seulgi.enums.provider.naver.NaverSortType;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class SearchDtoMapper {


    public static SearchBlogRes searchBlogResFrom(KakaoSearchBlogRes res, int page, int size) {
        return SearchBlogRes.builder()
                .total(res.getMeta().getPageableCount())
                .page(page)
                .size(size)
                .isEnd(res.getMeta().isEnd())
                .documents(res.getDocuments().stream()
                        .map(d -> Document.builder()
                                .title(d.getTitle())
                                .contents(d.getContents())
                                .url(d.getUrl())
                                .blogname(d.getBlogname())
                                .thumbnail(d.getThumbnail())
                                .datetime(d.getDatetime())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static SearchBlogRes searchBlogResFrom(NaverSearchBlogRes res) {
        return SearchBlogRes.builder()
                .total(res.getTotal())
                .page(res.getStart())
                .size(res.getDisplay())
                .isEnd(isEnd(res.getTotal(), res.getStart(), res.getDisplay()))
                .documents(res.getItems().stream()
                        .map(i -> Document.builder()
                                .title(i.getTitle())
                                .contents(i.getDescription())
                                .url(i.getLink())
                                .blogname(i.getBloggername())
                                .datetime(i.getPostdate())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static NaverSearchBlogReq naverSearchBlogReqFrom(SearchBlogReq req) {
        return NaverSearchBlogReq.builder()
                .query(req.getQuery())
                .start(req.getPage())
                .display(req.getSize())
                .sort(NaverSortType.getSortType(req.getSort()).getName())
                .build();
    }

    public static KakaoSearchBlogRes kakaoSearchBlogResFrom(NaverSearchBlogRes res) {
        return KakaoSearchBlogRes.builder()
                .meta(KakaoMeta.builder()
                        .totalCount(res.getTotal())
                        .pageableCount(res.getTotal())
                        .isEnd(isEnd(res.getTotal(), res.getStart(), res.getDisplay()))
                        .build())
                .documents(res.getItems().stream()
                        .map(i -> KakaoDocument.builder()
                                .title(i.getTitle())
                                .contents(i.getDescription())
                                .url(i.getLink())
                                .blogname(i.getBloggername())
                                .datetime(i.getPostdate())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static boolean isEnd(int total, int page, int size) {
        return total <= (page * size);
    }
}
