package com.seulgi.dto.provider.kakao;

import com.seulgi.domain.provider.kakao.KakaoDocument;
import com.seulgi.domain.provider.kakao.KakaoMeta;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoSearchBlogRes {

    KakaoMeta meta;

    List<KakaoDocument> documents;
}
