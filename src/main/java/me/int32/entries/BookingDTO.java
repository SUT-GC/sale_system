package me.int32.entries;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class BookingDTO {
    private String userName;

    private String userNick;

    private String userAddress;

    private String userPhone;

    private String deliveryNumber;

    private OrderStatusDTO orderStatus;

    private List<BookingCommodity> commodities;

    @Data
    public static class BookingCommodity {
        private Long id;
        private String name;
        private String sku;
        private Integer sellingNumber;
        private Double sellingFee;
    }

    public List<Long> getOrderCommodityIds() {
        if (commodities == null) {
            return new ArrayList<>();
        }

        return commodities.stream().filter(Objects::nonNull).map(BookingCommodity::getId).collect(Collectors.toList());
    }
}
