package me.int32.entries;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DataStatusDTO {
    INVALID(0, "无效"),
    VALID(1, "有效"),;

    @Getter
    private int value;
    @Getter
    private String desc;

    public static DataStatusDTO fromValue(int value) {
        for (DataStatusDTO dataStatusDTO : DataStatusDTO.values()) {
            if (dataStatusDTO.getValue() == value) {
                return dataStatusDTO;
            }
        }

        throw new RuntimeException("dataStatus has no value" + value);
    }
}
