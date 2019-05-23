package com.example.buyOrder.controller;

import com.example.buyOrder.model.OrderDTO;
import com.example.buyOrder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


   @RequestMapping("/buy")
    public OrderDTO getOrder(@RequestParam String email){
        return orderService.getOrderByEmail(email);
    }

    @PostMapping(value = "/buy")
    public OrderDTO createEmployee(@RequestBody OrderDTO orderDTO){
        try {
            return orderService.createOrder(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


   @PutMapping("/buy")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/buy")
    public OrderDTO deleteOrder(@RequestParam String email){
        return orderService.deleteOrder(email);
    }
}
