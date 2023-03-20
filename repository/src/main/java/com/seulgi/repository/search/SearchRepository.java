package com.seulgi.repository.search;

import com.seulgi.domain.search.Keyword;

import java.util.List;

public interface SearchRepository {

    void saveSearchKeyword(String keyword);

    List<Keyword> getPopularKeywords();
}
