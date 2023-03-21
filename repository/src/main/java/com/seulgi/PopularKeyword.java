package com.seulgi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PopularKeyword {

    @Id
    private String keyword;
    private Long count;
    private LocalDateTime createdAt;

    public static PopularKeyword of(String keyword, Long count) {
        return new PopularKeyword(keyword, count, LocalDateTime.now());
    }
}
