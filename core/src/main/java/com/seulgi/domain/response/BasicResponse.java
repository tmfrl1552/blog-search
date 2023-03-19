package com.seulgi.domain.response;

import com.seulgi.enums.ResponseCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// todo - basic response가 아니라 error 응답할때 code, message 주는 애로 변경할 수 있음 하기
public class BasicResponse<T> {
    int code;
    String message;
    T value;


    public BasicResponse(ResponseCode responseCode) {
        this.message = responseCode.getMessage();
        this.code = responseCode.getCode();
    }

    public BasicResponse(ResponseCode responseCode, T value) {
        this(responseCode);
        this.value = value;
    }

    public BasicResponse<T> appendMessage(Object msg) {
        this.message = String.format("%s[%s]", this.message, msg);
        return this;
    }
}
