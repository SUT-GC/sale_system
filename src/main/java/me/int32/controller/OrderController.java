package me.int32.controller;

import me.int32.entries.*;
import me.int32.service.api.CartService;
import me.int32.service.api.CommodityService;
import me.int32.service.api.OrderService;
import me.int32.service.bo.CartBO;
import me.int32.service.bo.CommodityBO;
import me.int32.service.bo.OrderBO;
import me.int32.transformer.CartTransformer;
import me.int32.transformer.CommodityTransformer;
import me.int32.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderTransformer orderTransformer;

    @Autowired
    private CommodityTransformer commodityTransformer;

    @Autowired
    private CartTransformer cartTransformer;

    @ResponseBody
    @RequestMapping("/all")
    public ResultDTO getAll() {
        try {
            List<OrderBO> orderBOS = orderService.getAll();
            List<Long> orderIds = orderBOS.stream().map(OrderBO::getId).collect(Collectors.toList());

            List<CartBO> cartBOS = cartService.findByOrderIds(orderIds);
            List<Long> commodityIds = cartBOS.stream().map(CartBO::getCommodityId).collect(Collectors.toList());

            List<CommodityBO> commodityBOS = commodityService.findByIds(commodityIds);

            List<OrderDTO> orderDTOS = genOrderDTO(orderBOS, cartBOS, commodityBOS);

            return ResultDTO.of(ResultStatusDTO.success(), orderDTOS);
        } catch (Exception e) {

            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }

    private List<OrderDTO> genOrderDTO(List<OrderBO> orderBOS, List<CartBO> cartBOS, List<CommodityBO> commodityBOS) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        orderBOS.forEach(orderBO -> {
            OrderDTO orderDTO = orderTransformer.transform(orderBO);
            List<CartDTO> orderCarts = cartTransformer.transform(cartBOS.stream().filter(p -> p.getOrderId() == orderBO.getId()).collect(Collectors.toList()));
            List<Long> orderCommodityIds = orderCarts.stream().map(CartDTO::getCommodityId).collect(Collectors.toList());
            List<CommodityDTO> orderCommodities = commodityTransformer.transform(commodityBOS.stream().filter(p -> orderCommodityIds.contains(p.getId())).collect(Collectors.toList()));

            orderDTO.setCarts(orderCarts);
            orderDTO.setCommodities(orderCommodities);

            orderDTOS.add(orderDTO);
        });

        return orderDTOS;
    }
}
