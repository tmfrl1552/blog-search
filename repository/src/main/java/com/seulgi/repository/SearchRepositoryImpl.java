package com.seulgi.repository;

import com.seulgi.SearchKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepository {

    private final SearchJpaRepository searchRepository;

    @Override
    public void saveSearchKeyword(String keyword) {
        SearchKeyword search = SearchKeyword.builder()
                .keyword(keyword)
                .createDatetime(new Timestamp(System.currentTimeMillis()))
                .build();

        searchRepository.save(search);
    }
}
