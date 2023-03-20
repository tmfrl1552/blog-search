package com.seulgi.repository;

import com.seulgi.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class SearchJpa {

    private final SearchRepository searchRepository;

    public void saveSearchKeyword(String keyword) {
        Search search = Search.builder()
                .keyword(keyword)
                .createDatetime(new Timestamp(System.currentTimeMillis()))
                .build();

        searchRepository.save(search);
    }
}
