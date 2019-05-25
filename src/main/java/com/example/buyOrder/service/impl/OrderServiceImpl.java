package com.example.buyOrder.service.impl;

import com.example.buyOrder.entity.Cart;
import com.example.buyOrder.entity.OrderEntity;
import com.example.buyOrder.model.OrderDTO;
import com.example.buyOrder.model.SuccessResponseDTO;
import com.example.buyOrder.repository.OrderRepository;
import com.example.buyOrder.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final String CART_SERVICE_ROOT_URL = "http://localhost:8085/";


    @Override
    public boolean createOrder(OrderDTO orderDTO , String token,String email)  {

        if(token == null)
            token = "";
        if(email == null)
            email = "";

        String ctoken = token;
        String cemail = email;
        final String uri = "http://localhost:8081/checktoken?token="+ctoken+"&email="+cemail;

        RestTemplate restTemplate = new RestTemplate();
        boolean status = restTemplate.getForObject(uri, boolean.class);


        if(status == true) {
            String cproductId = orderDTO.getProductId();
            String cvariantId = orderDTO.getVariantId();
            String cuseremail = orderDTO.getEmail();
            String cmerchantId = orderDTO.getMerchantId();

            final String url = "http://localhost:8085/deleteByemail?email="+cuseremail+"&productId="+cproductId+"&merchantId="+cmerchantId+"&variantId="+cvariantId;

            RestTemplate rest = new RestTemplate();
            restTemplate.delete(url, boolean.class);
//            System.out.println(status2);

            OrderEntity order = new OrderEntity();
            BeanUtils.copyProperties(orderDTO, order);
            OrderEntity result = orderRepository.save(order);
            OrderDTO resultDTO = new OrderDTO();
            BeanUtils.copyProperties(result, resultDTO);

            return true;
        }else{
            return false;
        }

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

    @Transactional
    @Override
    public SuccessResponseDTO buyEverythingFromCart(String email,String token) {

//        Checking authentication
        if(token == null)
            token = "";
        if(email == null)
            email = "";

        String ctoken = token;
        String cemail = email;
        final String uri = "http://localhost:8081/checktoken?token="+ctoken+"&email="+cemail;

        RestTemplate restTemplate = new RestTemplate();
        boolean status = restTemplate.getForObject(uri, boolean.class);

        if(status == false){
            return  new SuccessResponseDTO(false,"User not logged in ",email);
        }




        final String url = CART_SERVICE_ROOT_URL + "getEverythingFromCart?email="+email;
        restTemplate = new RestTemplate();
        Cart[] cartEntries = restTemplate.getForObject(url, Cart[].class);

        for(Cart cartEntry : cartEntries){
            OrderEntity order = new OrderEntity();
            order.setEmail(cartEntry.getEmail());
            order.setProductId(cartEntry.getProductId());
            order.setVariantId(cartEntry.getVariantId());
            order.setQuantity(cartEntry.getQuantity());
            order.setMerchantId(cartEntry.getMerchantId());

            orderRepository.save(order);

        }

        String deleteUrl = CART_SERVICE_ROOT_URL+"deleteCart?email="+email;
//        System.out.println(">>>>>>>>>>>>>>>>"+deleteUrl);
        restTemplate.delete(deleteUrl);



        return new SuccessResponseDTO(true,"Successfully transferred from cart to order ",email);
    }
}
