package com.seulgi.handler;

import com.seulgi.domain.response.BasicResponse;
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
public class SearchExceptionHandler {

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(SearchException.class
    )
    public BasicResponse<Object> exceptionHandler(SearchException e) {
        log.info(String.format("SearchException code : %s, message : %s",
                e.getResponseCode(), e.getMessage()), e);
        return new BasicResponse<>(e.getResponseCode());
    }
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(InvalidParameterException.class)
    public BasicResponse<Void> exceptionHandler(InvalidParameterException e) {
        log.info("InvalidParameterException : ", e);
        return new BasicResponse<Void>(ResponseCode.INVALID_PARAM).appendMessage(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BasicResponse<Object> exceptionHandler(MissingServletRequestParameterException e) {
        log.info("MissingServletRequestParameterException : ", e);
        return new BasicResponse<>(ResponseCode.INVALID_PARAM)
                .appendMessage(e.getParameterName());
    }
}
