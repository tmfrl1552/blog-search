package com.seulgi.domain.provider.kakao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Meta {

    int totalCount;

    int pageableCount;

    boolean isEnd;

}
