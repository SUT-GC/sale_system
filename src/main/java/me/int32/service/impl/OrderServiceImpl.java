package me.int32.service.impl;

import me.int32.dao.po.OrderPO;
import me.int32.dao.repositories.OrderRepository;
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

    @Override
    public OrderBO save(OrderBO orderBO) {
        OrderPO orderPO = orderTransformer.transform(orderBO);

        return orderTransformer.transform(orderRepository.save(orderPO));
    }

    @Override
    public OrderBO findById(Long id) {
        OrderPO orderPO = orderRepository.findById(id).orElse(null);


        return orderTransformer.transform(orderPO);
    }
}
