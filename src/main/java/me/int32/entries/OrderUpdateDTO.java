package me.int32.entries;

import lombok.Data;

@Data
public class OrderUpdateDTO {
    private Long id;
    private OrderStatusDTO orderStatus;
}
