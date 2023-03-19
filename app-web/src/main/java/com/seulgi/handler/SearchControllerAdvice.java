package com.seulgi.handler;

import com.seulgi.domain.response.ErrResponse;
import com.seulgi.enums.ErrorTypeCode;
import com.seulgi.exceptions.SearchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

@Slf4j
@Component
@RestControllerAdvice
public class SearchControllerAdvice {

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(SearchException.class)
    public ErrResponse<Object> exceptionHandler(SearchException e) {
        log.info(String.format("SearchException code : %s, message : %s",
                e.getResponseCode(), e.getMessage()), e);
        return new ErrResponse<>(e.getResponseCode());
    }
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(InvalidParameterException.class)
    public ErrResponse<Void> exceptionHandler(InvalidParameterException e) {
        log.info("InvalidParameterException : ", e);
        return new ErrResponse<Void>(ErrorTypeCode.INVALID_PARAM).appendMessage(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrResponse<Object> exceptionHandler(MissingServletRequestParameterException e) {
        log.info("MissingServletRequestParameterException : ", e);
        return new ErrResponse<>(ErrorTypeCode.INVALID_PARAM)
                .appendMessage(e.getParameterName());
    }
}
