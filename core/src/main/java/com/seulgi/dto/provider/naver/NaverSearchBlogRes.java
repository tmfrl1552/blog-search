package com.seulgi.dto.provider.naver;

import com.seulgi.domain.provider.naver.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NaverSearchBlogRes {

    Date lastBuildDate;

    int total;

    int start;

    int display;

    List<Item> items;

}
