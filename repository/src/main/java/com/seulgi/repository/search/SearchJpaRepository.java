package com.seulgi.repository.search;

import com.seulgi.SearchLog;
import com.seulgi.SearchKeywordInterface;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchJpaRepository extends JpaRepository<SearchLog, Long> {

    @Query("SELECT s.keyword AS keyword, COUNT(s.keyword) AS score " +
            "FROM SearchLog AS s " +
            "GROUP BY s.keyword " +
            "ORDER BY score DESC")
    List<SearchKeywordInterface> findPopularSearchKeywords(Pageable pageable);

}
