package com.seulgi.exceptions;

import com.seulgi.enums.ResponseCode;
import lombok.Getter;

@Getter
public class SearchException extends RuntimeException {

    private final ResponseCode responseCode;

    public SearchException(String message) {
        super(message);
        responseCode = ResponseCode.EXCEPTION_ERROR;
    }

    public SearchException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public SearchException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
