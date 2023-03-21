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
public class TrendKeyword {

    @Id
    private String keyword;
    private Long count;
    private LocalDateTime createdAt;

    public static TrendKeyword of(String keyword, Long count) {
        return new TrendKeyword(keyword, count, LocalDateTime.now());
    }
}
