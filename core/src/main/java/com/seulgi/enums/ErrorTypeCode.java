package com.seulgi.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorTypeCode {
    INVALID_PARAM(20000, "Invalid Param"),
    EXCEPTION_ERROR(99999, "Exception occurred"),
    SEARCH_SERVICE_PROVIDER_ERROR(200, "search service network error");

    int code;

    String message;
}
