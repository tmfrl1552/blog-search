package com.seulgi.dto.provider.kakao;

import com.seulgi.domain.provider.kakao.Document;
import com.seulgi.domain.provider.kakao.Meta;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogRes {

    Meta meta;

    List<Document> documents;
}
