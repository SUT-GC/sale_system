package me.int32.service.api;

import me.int32.service.bo.OrderBO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    List<OrderBO> getAll();

    OrderBO save(OrderBO orderBO);

    OrderBO findById(Long id);
}
