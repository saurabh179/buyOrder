package com.example.buyOrder.service;

import com.example.buyOrder.entity.OrderEntity;
import com.example.buyOrder.model.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO) ;
    List<OrderEntity> getOrderByEmail(String email);
    void deleteOrder(String email);
    OrderDTO updateOrder(OrderDTO orderDTO);
    }
