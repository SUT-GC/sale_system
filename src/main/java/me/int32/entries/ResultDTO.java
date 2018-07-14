package me.int32.entries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDTO {
    private ResultStatusDTO status;

    private Object data;

    public static ResultDTO of(ResultStatusDTO status, Object data) {
        return new ResultDTO(status, data);
    }
}
