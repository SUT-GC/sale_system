package me.int32.service.api;

import me.int32.service.bo.CartBO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {
    List<CartBO> findByOrderIds(List<Long> orderIds);

    List<CartBO> save(List<CartBO> cartBOS);
}
