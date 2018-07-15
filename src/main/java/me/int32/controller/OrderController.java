package me.int32.controller;

import me.int32.entries.*;
import me.int32.exception.InvalidParamException;
import me.int32.service.api.CartService;
import me.int32.service.api.CommodityService;
import me.int32.service.api.OrderService;
import me.int32.service.bo.CartBO;
import me.int32.service.bo.CommodityBO;
import me.int32.service.bo.OrderBO;
import me.int32.service.bo.OrderStatusBO;
import me.int32.transformer.CartTransformer;
import me.int32.transformer.CommodityTransformer;
import me.int32.transformer.OrderTransformer;
import me.int32.validate.ValidateBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
            List<OrderDTO> orderDTOS = orderTransformer.transformFromBO(orderBOS);

            orderDTOS = genOrderDTO(orderDTOS);

            return ResultDTO.of(ResultStatusDTO.success(), orderDTOS);
        } catch (Exception e) {

            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }

    @ResponseBody
    @RequestMapping("/booking")
    public ResultDTO booking(@RequestBody BookingDTO booking) {
        try {
            ValidateBooking.validate(booking);

            List<Long> commodityIds = booking.getOrderCommodityIds();
            List<CommodityBO> commodityBOS = commodityService.findByIds(commodityIds);

            if (CollectionUtils.isEmpty(commodityBOS)) {
                throw new InvalidParamException("订单商品不能为空");
            }

            commodityIds = commodityBOS.stream().map(CommodityBO::getId).collect(Collectors.toList());

            OrderBO orderBO = orderTransformer.transform(booking);
            orderBO = orderService.save(orderBO);

            List<CartBO> cartBOS = cartTransformer.genCartBO(orderBO, commodityBOS, booking.getCommodities());
            cartService.save(cartBOS);

            return ResultDTO.of(ResultStatusDTO.success(), null);
        } catch (Exception e) {

            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultDTO update(@RequestBody OrderUpdateDTO update) {
        try {
            ValidateBooking.validate(update);

            OrderBO orderBO = orderService.findById(update.getId());
            if (orderBO == null) {
                throw new InvalidParamException("未发现订单");
            }

            orderBO.setOrderStatus(OrderStatusBO.fromValue(update.getOrderStatus().getValue()));

            orderService.save(orderBO);

            return ResultDTO.of(ResultStatusDTO.success(), null);
        } catch (Exception e) {

            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }

    private List<OrderDTO> genOrderDTO(List<OrderDTO> orderDTOS) {
        List<Long> orderIds = orderDTOS.stream().map(OrderDTO::getId).collect(Collectors.toList());

        List<CartBO> cartBOS = cartService.findByOrderIds(orderIds);
        List<Long> commodityIds = cartBOS.stream().map(CartBO::getCommodityId).collect(Collectors.toList());

        List<CommodityBO> commodityBOS = commodityService.findByIds(commodityIds);

        orderDTOS.forEach(orderDTO -> {
            List<CartDTO> orderCarts = cartTransformer.transform(cartBOS.stream().filter(p -> p.getOrderId().equals(orderDTO.getId())).collect(Collectors.toList()));
            Double totalProfit = 0.0;
            for (CartDTO orderCart : orderCarts) {
                CommodityBO commodityBO = commodityBOS.stream().filter(p -> p.getId().equals(orderCart.getCommodityId())).findFirst().orElse(null);
                if (commodityBO == null) {
                    return;
                }

                totalProfit += orderCart.getProfit();
                orderCart.setCommodity(commodityTransformer.transform(commodityBO));
            }

            orderDTO.setTotalProfit(totalProfit);
            orderDTO.setCarts(orderCarts);
        });

        return orderDTOS;
    }
}
