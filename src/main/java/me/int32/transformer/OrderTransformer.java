package me.int32.transformer;

import me.int32.entries.BookingDTO;
import me.int32.entries.DataStatusDTO;
import me.int32.entries.OrderDTO;
import me.int32.entries.OrderStatusDTO;
import me.int32.service.bo.DataStatusBO;
import me.int32.service.bo.OrderBO;
import me.int32.service.bo.OrderStatusBO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderTransformer {
    public OrderDTO transformFromBO(OrderBO orderBO) {
        if (orderBO == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(orderBO.getId());
        orderDTO.setDataStatus(DataStatusDTO.fromValue(orderBO.getDataStatus().getValue()));
        orderDTO.setOrderStatus(OrderStatusDTO.fromValue(orderBO.getOrderStatus().getValue()));
        orderDTO.setDeliveryNumber(orderBO.getDeliveryNumber());
        orderDTO.setUserAddress(orderBO.getUserAddress());
        orderDTO.setUserName(orderBO.getUserName());
        orderDTO.setUserNick(orderBO.getUserNick());
        orderDTO.setUserPhone(orderBO.getUserPhone());
        orderDTO.setUpdatedAt(orderBO.getUpdatedAt());
        orderDTO.setCreatedAt(orderBO.getCreatedAt());
        orderDTO.setRemovedAt(orderBO.getRemovedAt());

        return orderDTO;
    }

    public List<OrderDTO> transformFromBO(List<OrderBO> orderBOS) {
        if (orderBOS == null) {
            return new ArrayList<>();
        }

        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderBO orderBO : orderBOS) {
            orderDTOS.add(transformFromBO(orderBO));
        }

        return orderDTOS;
    }

    public OrderBO transform(BookingDTO booking) {
        if (booking == null) {
            return null;
        }

        OrderBO orderBO = new OrderBO();

        orderBO.setUserName(booking.getUserName());
        orderBO.setUserNick(booking.getUserNick());
        orderBO.setUserAddress(booking.getUserAddress());
        orderBO.setUserPhone(booking.getUserPhone());
        orderBO.setDeliveryNumber(booking.getDeliveryNumber());
        orderBO.setOrderStatus(OrderStatusBO.fromValue(booking.getOrderStatus().getValue()));
        orderBO.setDataStatus(DataStatusBO.VALID);
        orderBO.setCreatedAt(LocalDateTime.now());
        orderBO.setUpdatedAt(LocalDateTime.now());
        orderBO.setRemovedAt(LocalDateTime.now());

        return orderBO;
    }
}
