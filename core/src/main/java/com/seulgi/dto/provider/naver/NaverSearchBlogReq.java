package com.seulgi.dto.provider.naver;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NaverSearchBlogReq {

    String query;

    int display;

    int start;

    String sort;

}
