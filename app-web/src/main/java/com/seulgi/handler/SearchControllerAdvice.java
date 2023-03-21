package com.seulgi.handler;

import com.seulgi.domain.response.RestResponse;
import com.seulgi.enums.ResponseCode;
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
    public RestResponse<Object> exceptionHandler(SearchException e) {
        log.info(String.format("SearchException code : %s, message : %s",
                e.getResponseCode(), e.getMessage()), e);
        return new RestResponse<>(e.getResponseCode());
    }
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(InvalidParameterException.class)
    public RestResponse<Void> exceptionHandler(InvalidParameterException e) {
        log.info("InvalidParameterException : ", e);
        return new RestResponse<Void>(ResponseCode.INVALID_PARAM).appendMessage(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RestResponse<Object> exceptionHandler(MissingServletRequestParameterException e) {
        log.info("MissingServletRequestParameterException : ", e);
        return new RestResponse<>(ResponseCode.INVALID_PARAM)
                .appendMessage(e.getParameterName());
    }
}
