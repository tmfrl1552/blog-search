package com.seulgi.domain.provider.kakao;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoDocument {

    String title;

    String contents;

    String url;

    String blogname;

    String thumbnail;

    Date datetime;
}
