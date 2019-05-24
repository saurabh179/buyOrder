package com.example.buyOrder.service.impl;

import com.example.buyOrder.entity.OrderEntity;
import com.example.buyOrder.model.OrderDTO;
import com.example.buyOrder.repository.OrderRepository;
import com.example.buyOrder.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO)  {


        OrderEntity order = new OrderEntity();
        BeanUtils.copyProperties(orderDTO, order);
        OrderEntity result = orderRepository.save(order);
        OrderDTO resultDTO = new OrderDTO();
        BeanUtils.copyProperties(result, resultDTO);

        return resultDTO;

    }

    @Override
    public List<OrderEntity> getOrderByEmail(String email) {
        List<OrderEntity> list  = orderRepository.findAllByEmail(email);
        return  list;
    }

    @Override
    public void deleteOrder(String email) {
        orderRepository.deleteByEmail(email);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderDTO,orderEntity);
        OrderEntity result = orderRepository.save(orderEntity);
        OrderDTO resultDTO = new OrderDTO();
        BeanUtils.copyProperties(result,resultDTO);
        return resultDTO;
    }
}
