package com.example.buyOrder.service;

import com.example.buyOrder.entity.OrderEntity;
import com.example.buyOrder.model.OrderDTO;
import com.example.buyOrder.model.SuccessResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    boolean createOrder(OrderDTO orderDTO,String token,String email) ;
    List<OrderEntity> getOrderByEmail(String email);
    void deleteOrder(String email);
    OrderDTO updateOrder(OrderDTO orderDTO);

    SuccessResponseDTO buyEverythingFromCart(String email, String token);
    }
