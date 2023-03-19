package com.seulgi.dto.provider.kakao;

import com.seulgi.domain.provider.kakao.Document;
import com.seulgi.domain.provider.kakao.Meta;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoSearchBlogRes {

    Meta meta;

    List<Document> documents;
}
