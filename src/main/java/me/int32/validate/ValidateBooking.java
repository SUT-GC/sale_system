package me.int32.validate;

import me.int32.entries.BookingDTO;
import me.int32.entries.OrderUpdateDTO;
import me.int32.exception.InvalidParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.stream.Collectors;

public class ValidateBooking {
    public static void validate(BookingDTO booking) throws InvalidParamException {
        ValidateNull.validateNull(booking);

        if (StringUtils.isEmpty(booking.getUserName())) {
            throw new InvalidParamException("用户名不能为空");
        }

        if (booking.getOrderStatus() == null) {
            throw new InvalidParamException("订单状态不能为空");
        }

        booking.setCommodities(booking.getCommodities().stream().filter(Objects::nonNull).collect(Collectors.toList()));

        if (CollectionUtils.isEmpty(booking.getCommodities())) {
            throw new InvalidParamException("订单商品不能为空");
        }

        for (BookingDTO.BookingCommodity bookingCommodity : booking.getCommodities()) {
            ValidateNumber.biggerThanZeroNumber(bookingCommodity.getId(), "商品ID");
            ValidateNumber.biggerThanZeroNumber(bookingCommodity.getSellingFee(), "商品售价");
            ValidateNumber.biggerThanZeroNumber(bookingCommodity.getSellingNumber(), "销售量");
        }
    }

    public static void validate(OrderUpdateDTO orderUpdateDTO) throws InvalidParamException {
        ValidateNull.validateNull(orderUpdateDTO);

        ValidateNumber.biggerThanZeroNumber(orderUpdateDTO.getId(), "订单ID");
        ValidateNull.validateNull(orderUpdateDTO.getOrderStatus());
    }
}
