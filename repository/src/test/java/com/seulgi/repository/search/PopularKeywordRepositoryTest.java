package com.seulgi.repository.search;

import com.seulgi.domain.search.Keyword;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class PopularKeywordRepositoryTest {

    RedisPopularKeywordRepository redisPopularKeywordRepository = mock(RedisPopularKeywordRepository.class);

    JpaPopularKeywordRepository jpaPopularKeywordRepository = mock(JpaPopularKeywordRepository.class);

    PopularKeywordRepository popularKeywordRepository = new PopularKeywordRepository(redisPopularKeywordRepository,
            jpaPopularKeywordRepository);



    @Test
    void getTop10PopularKeywordsTest() {
        // given
        List<Keyword> keywords = setKeywords();

        doReturn(keywords).when(redisPopularKeywordRepository)
                .findTop10ByOrderByCountDesc();

        // when
        List<Keyword> response = popularKeywordRepository.getTop10PopularKeywords();

        // then
        verify(redisPopularKeywordRepository).findTop10ByOrderByCountDesc();
        Assertions.assertThat(response.get(0).getKeyword()).isEqualTo(keywords.get(0).getKeyword());
        Assertions.assertThat(response.get(0).getScore()).isEqualTo(keywords.get(0).getScore());
    }

    static List<Keyword> setKeywords() {
        return Collections.singletonList(
                Keyword.of("kakao", 100L));
    }

}