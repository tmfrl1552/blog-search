package com.seulgi.enums.provider.naver;

import com.seulgi.enums.SearchSortType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum NaverSortType {

    SIM("sim", SearchSortType.ACCURACY),
    DATE("date", SearchSortType.RECENCY);

    final String name;

    final SearchSortType baseSortType;

    NaverSortType(String name, SearchSortType baseSortType) {
        this.name = name;
        this.baseSortType = baseSortType;
    }

    public static NaverSortType getSortType(SearchSortType baseSortType) {
        return Arrays.stream(NaverSortType.values())
                .filter(s -> s.getBaseSortType().equals(baseSortType))
                .findFirst()
                .orElse(NaverSortType.SIM);
    }
}
