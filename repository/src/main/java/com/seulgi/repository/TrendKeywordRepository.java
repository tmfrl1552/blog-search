package com.seulgi.repository;

import com.seulgi.TrendKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrendKeywordRepository extends JpaRepository<TrendKeyword, String> {

    List<TrendKeyword> findTop10TrendKeywordByOrderByCountDesc();
}
