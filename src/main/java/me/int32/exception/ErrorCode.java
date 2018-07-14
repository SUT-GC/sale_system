package me.int32.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {
    SERVICE_EXCEPTION(32768, "服务异常"),
    INVALID_PARAM_EXCEPTION(42768, "参数异常"),;

    @Getter
    private int code;
    @Getter
    private String desc;
}
