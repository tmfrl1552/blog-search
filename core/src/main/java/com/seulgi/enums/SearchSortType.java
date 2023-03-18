package com.seulgi.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum SearchSortType {
    ACCURACY("accuracy"),
    RECENCY("recency");

    final String name;

    SearchSortType(String name) {
        this.name = name;
    }
}
