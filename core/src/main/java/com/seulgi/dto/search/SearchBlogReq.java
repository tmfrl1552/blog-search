package com.seulgi.dto.search;

import com.seulgi.enums.SearchSortType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogReq {

    String query;

    SearchSortType sort;

    int page;

    int size;
}
