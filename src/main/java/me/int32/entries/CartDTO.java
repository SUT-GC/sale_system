package me.int32.entries;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartDTO {
    private Long id;

    private Long commodityId;

    private Long orderId;

    private Integer sellingNumber;

    private Double sellingFee;

    private Double profit;

    private DataStatusDTO dataStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;
}
