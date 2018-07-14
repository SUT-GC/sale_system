package me.int32.service.impl;

import me.int32.core.dao.repositories.OrderRepository;
import me.int32.service.api.OrderService;
import me.int32.service.bo.OrderBO;
import me.int32.service.transformer.OrderTransformerBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderTransformerBO orderTransformer;

    @Override
    public List<OrderBO> getAll() {
        return orderTransformer.transform(orderRepository.findAll());
    }
}
