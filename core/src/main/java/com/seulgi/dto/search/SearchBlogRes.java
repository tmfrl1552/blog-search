package com.seulgi.dto.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seulgi.domain.search.Document;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogRes {

    int total;

    int page;

    int size;

    @JsonProperty("is_end")
    boolean isEnd;

    List<Document> documents;
}
