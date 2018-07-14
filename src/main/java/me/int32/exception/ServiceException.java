package me.int32.exception;

import lombok.Data;

@Data
public abstract class ServiceException extends Exception {
    private int code;
    private String message;

    public ServiceException(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    public ServiceException(ErrorCode code, String message) {
        this.code = code.getCode();
        this.message = message;
    }


}
