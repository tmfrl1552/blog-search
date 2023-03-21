package com.seulgi.exceptions;

import com.seulgi.enums.ResponseCode;
import lombok.Getter;

@Getter
public class SearchException extends RuntimeException {

    private final ResponseCode responseCode;

    public SearchException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

}
