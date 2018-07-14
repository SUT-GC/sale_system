package me.int32.service.transformer;

import me.int32.dao.po.OrderPO;
import me.int32.service.bo.DataStatusBO;
import me.int32.service.bo.OrderBO;
import me.int32.service.bo.OrderStatusBO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderTransformerBO {
    public List<OrderBO> transform(List<OrderPO> orderPOS) {
        if (orderPOS == null) {
            return null;
        }

        List<OrderBO> orderBOS = new ArrayList<>();

        orderPOS.forEach(p -> {
            orderBOS.add(transform(p));
        });

        return orderBOS;
    }

    public OrderBO transform(OrderPO orderPO) {
        if (orderPO == null) {
            return null;
        }

        OrderBO orderBO = new OrderBO();

        orderBO.setId(orderPO.getId());
        orderBO.setUserName(orderPO.getUserName());
        orderBO.setUserNick(orderPO.getUserNick());
        orderBO.setUserAddress(orderPO.getUserAddress());
        orderBO.setUserPhone(orderPO.getUserPhone());
        orderBO.setDeliveryNumber(orderPO.getDeliveryNumber());
        orderBO.setDataStatus(DataStatusBO.fromValue(orderPO.getDataStatus()));
        orderBO.setOrderStatus(OrderStatusBO.fromValue(orderPO.getStatus()));
        orderBO.setCreatedAt(orderPO.getCreatedAt());
        orderBO.setUpdatedAt(orderPO.getUpdatedAt());
        orderBO.setRemovedAt(orderPO.getRemovedAt());

        return orderBO;
    }

    public OrderPO transform(OrderBO orderBO) {
        if (orderBO == null) {
            return null;
        }

        OrderPO orderPO = new OrderPO();

        orderPO.setId(orderBO.getId());
        orderPO.setUserName(orderBO.getUserName());
        orderPO.setUserNick(orderBO.getUserNick());
        orderPO.setUserAddress(orderBO.getUserAddress());
        orderPO.setUserPhone(orderBO.getUserPhone());
        orderPO.setDeliveryNumber(orderBO.getDeliveryNumber());
        orderPO.setDataStatus(orderBO.getDataStatus() == null ? 0 : orderBO.getDataStatus().getValue());
        orderPO.setStatus(orderBO.getOrderStatus() == null ? 0 : orderBO.getDataStatus().getValue());
        orderPO.setCreatedAt(orderBO.getCreatedAt());
        orderPO.setUpdatedAt(orderBO.getUpdatedAt());
        orderPO.setRemovedAt(orderBO.getRemovedAt());

        return orderPO;
    }
}
