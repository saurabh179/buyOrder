package com.example.buyOrder.controller;

import com.example.buyOrder.entity.OrderEntity;
import com.example.buyOrder.model.OrderDTO;
import com.example.buyOrder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

   @RequestMapping(method = RequestMethod.GET, value = "/getOrdersByEmail")
    public List<OrderEntity> getOrder(@RequestParam String email){
        return orderService.getOrderByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addOrder")
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO){
        try {
            return orderService.createOrder(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


   @RequestMapping(method = RequestMethod.POST, value = "/updateOrder")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteOrder")
    public void deleteOrder(@RequestParam String email){
        orderService.deleteOrder(email);
    }
}
