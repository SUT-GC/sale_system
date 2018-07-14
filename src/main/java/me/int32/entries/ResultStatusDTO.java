package me.int32.entries;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.int32.exception.ServiceException;

@Data
@AllArgsConstructor
public class ResultStatusDTO {
    private int code;
    private String desc;

    public static ResultStatusDTO success() {
        return new ResultStatusDTO(200, "请求成功");
    }

    public static ResultStatusDTO error(Exception exception) {
        if (exception instanceof ServiceException) {
            return new ResultStatusDTO(((ServiceException) exception).getCode(), exception.getMessage());
        } else {
            return new ResultStatusDTO(500, "服务器错误");
        }
    }
}
