package me.int32.service.impl;

import me.int32.dao.po.CartPO;
import me.int32.dao.repositories.CartRepository;
import me.int32.service.api.CartService;
import me.int32.service.bo.CartBO;
import me.int32.service.transformer.CartTransformerBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartTransformerBO cartTransformer;

    @Override
    public List<CartBO> findByOrderIds(List<Long> orderIds) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return new ArrayList<>();
        }

        return cartTransformer.transformPO(cartRepository.findByOrderIds(orderIds));
    }

    @Override
    public List<CartBO> save(List<CartBO> cartBOS) {
        List<CartPO> cartPOS = cartTransformer.transformFromBO(cartBOS);

        cartPOS.forEach(p -> {
            p = cartRepository.save(p);
        });

        return cartTransformer.transformPO(cartPOS);
    }
}
