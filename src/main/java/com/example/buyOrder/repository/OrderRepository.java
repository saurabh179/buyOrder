package com.example.buyOrder.repository;

import com.example.buyOrder.entity.OrderEntity;
import com.example.buyOrder.model.OrderDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends CrudRepository<OrderEntity,String> {
 List<OrderEntity> findAllByEmail(String email);
 void deleteByEmail(String email);
}
