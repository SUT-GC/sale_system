package me.int32.service.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatusBO {
    INVALID(0, "无效订单"),
    UNPAID(100, "未收钱"),
    PAID_UN_DELIVERY(200, "已收钱未发货"),
    PAID_DELIVERY(300, "收钱已发货"),
    SIGNED(400, "签收"),;

    @Getter
    private int value;
    @Getter
    private String desc;

    public static OrderStatusBO fromValue(int value) {
        for (OrderStatusBO dataStatus : OrderStatusBO.values()) {
            if (dataStatus.getValue() == value) {
                return dataStatus;
            }
        }

        throw new RuntimeException("OrderStatusBO has no value" + value);
    }
}
