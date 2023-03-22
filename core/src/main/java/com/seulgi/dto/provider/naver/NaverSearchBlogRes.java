package com.seulgi.dto.provider.naver;

import com.seulgi.domain.provider.naver.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NaverSearchBlogRes {

    Date lastBuildDate;

    int total;

    int start;

    int display;

    List<Item> items;

}
