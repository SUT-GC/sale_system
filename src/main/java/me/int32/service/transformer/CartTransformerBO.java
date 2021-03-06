package me.int32.service.transformer;

import me.int32.dao.po.CartPO;
import me.int32.service.bo.CartBO;
import me.int32.service.bo.DataStatusBO;
import me.int32.util.DataTypeUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartTransformerBO {
    public List<CartBO> transformPO(List<CartPO> cartPOS) {
        if (cartPOS == null) {
            return null;
        }

        List<CartBO> cartBOS = new ArrayList<>();

        cartPOS.forEach(p -> {
            cartBOS.add(transformPO(p));
        });

        return cartBOS;
    }

    public CartBO transformPO(CartPO cartPO) {
        if (cartPO == null) {
            return null;
        }

        CartBO cartBO = new CartBO();

        cartBO.setId(cartPO.getId());
        cartBO.setOrderId(cartPO.getOrderId());
        cartBO.setCommodityId(cartPO.getCommodityId());
        cartBO.setProfit(DataTypeUtil.intToDoublePrice(cartPO.getProfit()));
        cartBO.setSellingFee(DataTypeUtil.intToDoublePrice(cartPO.getSellingFee()));
        cartBO.setSellingNumber(cartPO.getSellingNumber());
        cartBO.setDataStatus(DataStatusBO.fromValue(cartPO.getDataStatus()));
        cartBO.setRemovedAt(cartPO.getRemovedAt());
        cartBO.setCreatedAt(cartPO.getCreatedAt());
        cartBO.setUpdatedAt(cartPO.getUpdatedAt());

        return cartBO;
    }

    public CartPO transformFromBO(CartBO cartBO) {
        if (cartBO == null) {
            return null;
        }

        CartPO cartPO = new CartPO();

        cartPO.setId(cartBO.getId());
        cartPO.setOrderId(cartBO.getOrderId());
        cartPO.setCommodityId(cartBO.getCommodityId());
        cartPO.setProfit(Optional.ofNullable(cartBO.getProfit()).map(DataTypeUtil::doubleToIntPrice).orElse(0));
        cartPO.setSellingFee(Optional.ofNullable(cartBO.getSellingFee()).map(DataTypeUtil::doubleToIntPrice).orElse(0));
        cartPO.setSellingNumber(cartBO.getSellingNumber());
        cartPO.setDataStatus(Optional.ofNullable(cartBO.getDataStatus()).map(DataStatusBO::getValue).orElse(0));
        cartPO.setRemovedAt(cartBO.getRemovedAt());
        cartPO.setCreatedAt(cartBO.getCreatedAt());
        cartPO.setUpdatedAt(cartBO.getUpdatedAt());

        return cartPO;
    }

    public List<CartPO> transformFromBO(List<CartBO> cartBOS) {
        if (cartBOS == null) {
            return null;
        }

        return cartBOS.stream().map(this::transformFromBO).collect(Collectors.toList());
    }
}
