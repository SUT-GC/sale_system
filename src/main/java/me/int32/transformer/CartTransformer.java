package me.int32.transformer;

import me.int32.entries.BookingDTO;
import me.int32.entries.CartDTO;
import me.int32.entries.DataStatusDTO;
import me.int32.service.bo.CartBO;
import me.int32.service.bo.CommodityBO;
import me.int32.service.bo.OrderBO;
import me.int32.util.DataTypeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CartTransformer {
    public List<CartDTO> transform(List<CartBO> cartBOS) {
        List<CartDTO> cartDTOS = new ArrayList<>();

        if (cartBOS == null) {
            return cartDTOS;
        }

        cartBOS.stream().filter(Objects::nonNull).forEach(p -> {
            cartDTOS.add(transform(p));
        });

        return cartDTOS;
    }

    private CartDTO transform(CartBO cartBO) {
        if (cartBO == null) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();

        cartDTO.setId(cartBO.getId());
        cartDTO.setCommodityId(cartBO.getCommodityId());
        cartDTO.setOrderId(cartBO.getOrderId());
        cartDTO.setCreatedAt(cartBO.getCreatedAt());
        cartDTO.setDataStatus(DataStatusDTO.fromValue(cartBO.getDataStatus().getValue()));
        cartDTO.setProfit(cartBO.getProfit());
        cartDTO.setRemovedAt(cartBO.getRemovedAt());
        cartDTO.setSellingFee(cartBO.getSellingFee());
        cartDTO.setSellingNumber(cartBO.getSellingNumber());
        cartDTO.setUpdatedAt(cartBO.getUpdatedAt());

        return cartDTO;
    }

    public List<CartBO> genCartBO(OrderBO orderBO, List<CommodityBO> commodities, List<BookingDTO.BookingCommodity> bookingCommodities) {
        List<CartBO> cartBOS = new ArrayList<>();

        commodities.forEach(commodity -> {
            BookingDTO.BookingCommodity bookingCommodity = bookingCommodities.stream().filter(p -> p.getId().equals(commodity.getId())).findFirst().orElse(null);
            if (bookingCommodity == null) {
                return;
            }

            CartBO cartBO = new CartBO();
            cartBO.setOrderId(orderBO.getId());
            cartBO.setCommodityId(bookingCommodity.getId());
            cartBO.setSellingNumber(bookingCommodity.getSellingNumber());
            cartBO.setSellingFee(bookingCommodity.getSellingFee());
            cartBO.setProfit((bookingCommodity.getSellingFee() - DataTypeUtil.intToDoublePrice(commodity.getPurchaseFee())) * bookingCommodity.getSellingNumber());
            cartBO.setCreatedAt(LocalDateTime.now());
            cartBO.setUpdatedAt(LocalDateTime.now());
            cartBO.setRemovedAt(LocalDateTime.now());

            cartBOS.add(cartBO);
        });

        return cartBOS;
    }
}
