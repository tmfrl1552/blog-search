package com.seulgi.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ResponseCode {
    RESPONSE_OK(0, "Success"),
    INVALID_PARAM(20000, "Invalid Param"),
    EXCEPTION_ERROR(99999, "Exception occurred"),
    SEARCH_SERVICE_PROVIDER_ERROR(2500, "search service network error"),
    REDIS_RESPONSE_ERROR(80000, "redis response error");

    int code;

    String message;
}
