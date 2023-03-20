package com.seulgi.repository.search;

import com.seulgi.SearchKeyword;
import com.seulgi.SearchKeywordInterface;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchJpaRepository extends JpaRepository<SearchKeyword, Long> {

    @Query("SELECT s.keyword AS keyword, COUNT(s.keyword) AS score " +
            "FROM SearchKeyword AS s " +
            "GROUP BY s.keyword " +
            "ORDER BY COUNT(s.keyword) DESC")
    List<SearchKeywordInterface> findPopularSearchKeywords(Pageable pageable);

}
