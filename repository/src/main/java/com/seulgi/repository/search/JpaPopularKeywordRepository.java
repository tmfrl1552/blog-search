package com.seulgi.repository.search;

import com.seulgi.PopularKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPopularKeywordRepository extends JpaRepository<PopularKeyword, String> {

    List<PopularKeyword> findTop10PopularKeywordByOrderByScoreDesc();
}
