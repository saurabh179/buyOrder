package com.example.buyOrder.controller;

import com.example.buyOrder.entity.OrderEntity;
import com.example.buyOrder.model.OrderDTO;
import com.example.buyOrder.model.SuccessResponseDTO;
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
    public boolean addOrder(@RequestBody OrderDTO orderDTO,@RequestParam String token,@RequestParam String email){
        try {
            return orderService.createOrder(orderDTO,token,email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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


    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/buyEverythingFromCart")
    public SuccessResponseDTO buyEverythingFromCart(@RequestParam String email, @RequestParam String token){
        return orderService.buyEverythingFromCart(email,token);
    }


}
