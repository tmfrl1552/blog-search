package com.seulgi.domain.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Document {

    String title;

    String contents;

    String url;

    String blogname;

    String thumbnail;

    Date datetime;
}
