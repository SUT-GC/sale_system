package me.int32.validate;

import me.int32.exception.InvalidParamException;

public class ValidateNull {
    public static void validateNull(Object object) throws InvalidParamException {
        if (object == null) {
            throw new InvalidParamException();
        }
    }
}
