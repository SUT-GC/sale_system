package me.int32.service.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartBO {
    private Long id;

    private Long commodityId;

    private Long orderId;

    private Integer sellingNumber;

    private Double sellingFee;

    private Double profit;

    private DataStatusBO dataStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;
}
