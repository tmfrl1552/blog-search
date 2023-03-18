package com.seulgi.dto.search;

import com.seulgi.enums.SearchSortType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogsReq {

    String query;

    SearchSortType sort;

    int page;

    int size;
}
