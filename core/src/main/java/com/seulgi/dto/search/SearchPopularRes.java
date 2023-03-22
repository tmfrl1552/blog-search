package com.seulgi.dto.search;

import com.seulgi.domain.search.Keyword;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchPopularRes {

    List<Keyword> keywords;

}
