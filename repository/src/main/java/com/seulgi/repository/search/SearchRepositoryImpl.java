package com.seulgi.repository.search;

import com.seulgi.SearchKeyword;
import com.seulgi.SearchKeywordInterface;
import com.seulgi.domain.search.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepository {

    private final SearchJpaRepository searchRepository;

    @Override
    @Async
    public void saveSearchKeyword(String keyword) {
        SearchKeyword search = SearchKeyword.builder()
                .keyword(keyword)
                .createDatetime(new Timestamp(System.currentTimeMillis()))
                .build();

        searchRepository.save(search);
    }

    public List<Keyword> getPopularKeywords() {
        List<SearchKeywordInterface> searchKeywords = searchRepository.findPopularSearchKeywords(
                PageRequest.of(0,10));

        return searchKeywords.stream()
                .map(s -> Keyword.builder()
                        .keyword(s.getKeyword())
                        .score(s.getScore())
                        .build())
                .collect(Collectors.toList());
    }
}
