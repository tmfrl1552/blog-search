package com.seulgi.dto.search;

import com.seulgi.domain.search.Document;
import com.seulgi.domain.search.Meta;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogRes {

    Meta meta;

    List<Document> documents;
}
