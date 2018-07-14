package me.int32.transformer;

import me.int32.entries.DataStatusDTO;
import me.int32.entries.OrderDTO;
import me.int32.entries.OrderStatusDTO;
import me.int32.service.bo.OrderBO;
import org.springframework.stereotype.Component;

@Component
public class OrderTransformer {
    public OrderDTO transform(OrderBO orderBO) {
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
}
