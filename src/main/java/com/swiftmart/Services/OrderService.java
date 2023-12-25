package com.swiftmart.Services;

import com.swiftmart.Enums.OrderStatus;
import com.swiftmart.Models.Order;
import com.swiftmart.Models.Samples.OrderInfo;
import com.swiftmart.Models.User;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Order create(User sale, User user, Double total)
    {
        Order order = new Order();
        order.setUserId(user.get_id());
        order.setSaleId(sale.get_id());
        order.setLocationId(sale.getLocationId());
        order.setTotal(total);
        order.setPaymentMethod("BANK");
        order.setReceivedMoney(0.0);
        order.setGaveBackMoney(0.0);
        order.setCreatedAt(new Date());

        repository.getOrderRepository().save(order);

        return order;
    }

    public void updateTimeout(Order order)
    {
        order.setStatus(OrderStatus.FAILED.name());
        repository.getOrderRepository().save(order);
    }

    public void updatePaymentBankSuccess(Order order)
    {
        order.setStatus(OrderStatus.SUCCESS.name());
        order.setReceivedMoney(order.getTotal());
        order.setGaveBackMoney(order.getTotal());
        repository.getOrderRepository().save(order);
    }

}