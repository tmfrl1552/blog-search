package com.seulgi.domain.provider.naver;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

    String title;

    String link;

    String description;

    String bloggername;

    String bloggerlink;

    Date postdate;

}
