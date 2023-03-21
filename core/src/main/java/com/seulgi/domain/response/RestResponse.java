package com.seulgi.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seulgi.enums.ResponseCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestResponse<T> {
    private String message;
    private int code;
    private T value;
    @JsonIgnore
    private Map<String, Object> param = Collections.emptyMap();

    public RestResponse(ResponseCode responseCode) {
        this.message = responseCode.getMessage();
        this.code = responseCode.getCode();
    }

    public RestResponse(ResponseCode responseCode, Map<String, Object> param) {
        this.message = responseCode.getMessage();
        this.code = responseCode.getCode();
        this.param = param;
    }

    public RestResponse(ResponseCode responseCode, T value) {
        this(responseCode);
        this.value = value;
    }

    public RestResponse(ResponseCode responseCode, T value, Map<String, Object> param) {
        this(responseCode);
        this.value = value;
        this.param = param;
    }

    public RestResponse<T> appendMessage(Object msg) {
        this.message = String.format("%s[%s]", this.message, msg);
        return this;
    }

    public static <T> RestResponse<T> success(T value) {
        return new RestResponse<>(ResponseCode.RESPONSE_OK, value);
    }
}
