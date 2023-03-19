package com.seulgi.domain.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meta {

    int total;

    int page;

    int size;

    boolean isEnd;
}
