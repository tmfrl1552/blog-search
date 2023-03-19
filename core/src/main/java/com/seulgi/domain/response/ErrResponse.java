package com.seulgi.domain.response;

import com.seulgi.enums.ResponseCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrResponse<T> {
    int code;
    String message;


    public ErrResponse(ResponseCode responseCode) {
        this.message = responseCode.getMessage();
        this.code = responseCode.getCode();
    }

    public ErrResponse<T> appendMessage(Object msg) {
        this.message = String.format("%s[%s]", this.message, msg);
        return this;
    }
}
