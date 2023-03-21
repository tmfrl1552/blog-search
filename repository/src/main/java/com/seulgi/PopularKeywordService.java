package com.seulgi;

import com.seulgi.domain.search.Keyword;
import com.seulgi.repository.search.RedisPopularKeywordRepository;
import com.seulgi.repository.search.PopularKeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class PopularKeywordService {

    private final RedisPopularKeywordRepository redisPopularKeywordRepository;
    private final PopularKeywordRepository popularKeywordRepository;

    public void updateCountByKeyword(String keyword) {
        redisPopularKeywordRepository.updateCountByKeyword(keyword);
    }

    public List<Keyword> getTop10TrendKeywordsLookAside() {
        try {
            return redisPopularKeywordRepository.findTop10ByOrderByCountDesc();
        } catch (Exception e) {
            log.error("Failed, get the redis cache trend keyword, {}", e.getMessage());
        }

        return popularKeywordRepository.findTop10TrendKeywordByOrderByCountDesc().stream()
                .map(t -> Keyword.of(t.getKeyword(), t.getCount())).collect(toList());
    }

    @Transactional
    @Scheduled(fixedDelay = 1000 * 60 * 10)
    public void backupTrendKeyword() {
        List<Keyword> redisTop10Keywords = redisPopularKeywordRepository.findTop10ByOrderByCountDesc();
        List<PopularKeyword> list = redisTop10Keywords.stream()
                .map(t -> PopularKeyword.of(t.getKeyword(), t.getCount()))
                .collect(toList());

        log.info("Back up  redis data in database : " + LocalDateTime.now());
        popularKeywordRepository.saveAll(list);
    }

}
