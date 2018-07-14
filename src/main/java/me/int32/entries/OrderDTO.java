package me.int32.entries;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class OrderDTO {
    private Long id;

    private String userName;

    private String userNick;

    private String userPhone;

    private String userAddress;

    private String deliveryNumber;

    private List<CommodityDTO> commodities;

    private List<CartDTO> carts;

    private OrderStatusDTO orderStatus;

    private DataStatusDTO dataStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;
}
