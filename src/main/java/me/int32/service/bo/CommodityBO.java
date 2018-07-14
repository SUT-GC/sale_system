package me.int32.service.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommodityBO {
    private Long id;

    private String name;

    private String sku;

    private Integer purchaseFee;

    private Integer customFee;

    private Integer sellingNum;

    private Integer stock;

    private CommoditySourceBO source;

    private DataStatusBO dataStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;
}
