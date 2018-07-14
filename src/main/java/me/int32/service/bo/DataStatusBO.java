package me.int32.service.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DataStatusBO {
    INVALID(0, "无效"),

    VALID(1, "有效"),;

    @Getter
    private int value;
    @Getter
    private String desc;

    public static DataStatusBO fromValue(int value) {
        for (DataStatusBO dataStatusBO : DataStatusBO.values()) {
            if (dataStatusBO.getValue() == value) {
                return dataStatusBO;
            }
        }

        throw new RuntimeException("DataStatusBO has no value" + value);
    }
}
