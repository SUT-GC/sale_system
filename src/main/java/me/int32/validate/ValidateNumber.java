package me.int32.validate;

import me.int32.exception.InvalidParamException;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidateNumber {
    public static void biggerThanZeroNumber(Object o, String message) throws InvalidParamException {
        if (!NumberUtils.isNumber(o.toString()) || Double.parseDouble(o.toString()) <= 0.0) {
            throw new InvalidParamException(String.format("%s必须大于0", message));
        }
    }
}
