package com.swiftmart.Repositories;

import com.swiftmart.Models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>
{

    List<Order> getOrdersByUserId(String userId);
    List<Order> getOrdersByCreatedAtBetween(Date start, Date end);
    Order findBy_id(String orderId);

}