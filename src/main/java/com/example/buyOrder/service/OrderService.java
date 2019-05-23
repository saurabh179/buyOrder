package com.example.buyOrder.service;

import com.example.buyOrder.model.OrderDTO;
import org.springframework.stereotype.Service;



public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO) ;
    OrderDTO getOrderByEmail(String email);
    OrderDTO deleteOrder(String email);
    OrderDTO updateOrder(OrderDTO OrderDTO);
    }
