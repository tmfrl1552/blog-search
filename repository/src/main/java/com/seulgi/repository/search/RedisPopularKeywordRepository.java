package com.seulgi.repository.search;

import com.seulgi.domain.search.Keyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisPopularKeywordRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public void updateCountByKeyword(String keyword) {
        try {
            redisTemplate.opsForZSet().incrementScore("keyword", keyword, 1d);
        } catch (Exception e) {
            log.error("Fail, increment score by redis cache, {}", e.getMessage());
        }
    }

    public List<Keyword> findTop10ByOrderByCountDesc() {

        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        Set<TypedTuple<String>> tuples = zSet.reverseRangeWithScores("keyword", 0, 9);

        if (Objects.isNull(tuples)) {
            return Collections.emptyList();
        }
        return tuples.stream()
                .map(tuple -> Keyword.of(tuple.getValue(),
                        Objects.requireNonNull(tuple.getScore()).longValue()))
                .collect(toList());
    }

}
