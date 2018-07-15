package me.int32.service.bo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderBO {
    private Long id;

    private String userName;

    private String userNick;

    private String userPhone;

    private String userAddress;

    private String deliveryNumber;

    private OrderStatusBO orderStatus;

    private DataStatusBO dataStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;
}
