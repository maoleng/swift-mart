package com.swiftmart.Repositories;

import com.swiftmart.Models.OrderProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends MongoRepository<OrderProduct, String>
{

    List<OrderProduct> getOrderProductsByProductId(String productId);
    List<OrderProduct> getOrderProductsByOrderId(String orderId);

}