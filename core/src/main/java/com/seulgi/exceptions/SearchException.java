package com.seulgi.exceptions;

import com.seulgi.enums.ErrorTypeCode;
import lombok.Getter;

@Getter
public class SearchException extends RuntimeException {

    private final ErrorTypeCode responseCode;

    public SearchException(String message) {
        super(message);
        responseCode = ErrorTypeCode.EXCEPTION_ERROR;
    }

    public SearchException(ErrorTypeCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public SearchException(ErrorTypeCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
