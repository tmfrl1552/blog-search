package com.seulgi;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SearchLog", indexes = @Index(name = "idx__keyword", columnList = "keyword"))
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String keyword;

    private Timestamp createDatetime;
}
