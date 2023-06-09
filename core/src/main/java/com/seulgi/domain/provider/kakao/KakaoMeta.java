package com.seulgi.domain.provider.kakao;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoMeta {

    int totalCount;

    int pageableCount;

    boolean isEnd;

}
