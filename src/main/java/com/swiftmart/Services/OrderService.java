package com.swiftmart.Services;

import com.swiftmart.Models.Order;
import com.swiftmart.Models.Samples.OrderInfo;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends BaseService
{

    public OrderService(Repository repository, MongoTemplate mongoTemplate)
    {
        super(repository, mongoTemplate);
    }

    public List<Order> getOrdersByUserId(String userId)
    {
        return repository.getOrderRepository().getOrdersByUserId(userId);
    }

    public OrderInfo getOrderInfo(String orderId)
    {
        return new OrderInfo(
            repository.getOrderRepository().findBy_id(orderId),
            repository.getOrderProductRepository().getOrderProductsByOrderId(orderId)
        );
    }

}