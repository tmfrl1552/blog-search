package com.seulgi.dto.search;

import com.seulgi.domain.search.Keyword;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchPopularRes {

    List<Keyword> keywords;

}
