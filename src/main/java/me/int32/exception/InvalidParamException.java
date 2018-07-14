package me.int32.exception;

public class InvalidParamException extends ServiceException {

    public InvalidParamException() {
        super(ErrorCode.INVALID_PARAM_EXCEPTION);
    }

    public InvalidParamException(ErrorCode code) {
        super(code);
    }

    public InvalidParamException(String message) {
        super(ErrorCode.INVALID_PARAM_EXCEPTION, message);
    }

    public InvalidParamException(ErrorCode code, String message) {
        super(code, message);
    }
}
