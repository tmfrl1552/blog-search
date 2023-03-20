package com.seulgi.repository;

import com.seulgi.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchJpaRepository extends JpaRepository<SearchKeyword, Long> {

}
