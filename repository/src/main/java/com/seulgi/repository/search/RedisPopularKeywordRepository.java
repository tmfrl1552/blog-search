package com.seulgi.repository.search;

import com.seulgi.domain.search.Keyword;
import com.seulgi.enums.ResponseCode;
import com.seulgi.exceptions.SearchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
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

    final static String POPULAR_KEYWORD_KEY = "keyword";


    private final RedisTemplate<String, String> redisTemplate;

    public void updateScoreByKeyword(String keyword) {
        try {
            redisTemplate.execute(new SessionCallback<>() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {
                    operations.multi();
                    operations.opsForZSet().incrementScore(POPULAR_KEYWORD_KEY, keyword, 1d);
                    operations.exec();
                    return null;
                }
            });
        } catch (Exception e) {
            log.error("Fail, increment score by redis cache, {}", e.getMessage());
        }
    }

    public List<Keyword> findTop10ByOrderByCountDesc() {
        Set<TypedTuple<String>> tuples;
        List<Keyword> result = Collections.emptyList();

        try {
            ZSetOperations<String, String> ZSetOperations = redisTemplate.opsForZSet();
            tuples = ZSetOperations.reverseRangeWithScores(POPULAR_KEYWORD_KEY, 0, 9);
        } catch (Exception e) {
            log.error("Fail, find 10 popular keywords by redis cache, {}", e.getMessage());
            throw new SearchException(ResponseCode.REDIS_RESPONSE_ERROR);
        }

        if (Objects.nonNull(tuples)) {
            result = tuples.stream()
                    .map(tuple -> Keyword.of(tuple.getValue(),
                            Objects.requireNonNull(tuple.getScore()).longValue()))
                    .collect(toList());
        }

        return result;
    }
}
