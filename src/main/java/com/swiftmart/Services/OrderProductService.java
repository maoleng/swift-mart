package com.swiftmart.Services;

import com.swiftmart.Models.OrderProduct;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService extends BaseService
{


    public OrderProductService(Repository repository, MongoTemplate mongoTemplate) {
        super(repository, mongoTemplate);
    }

    public int sumOrderedProduct(String productId)
    {
        List<OrderProduct> orderProducts = repository.getOrderProductRepository().getOrderProductsByProductId(productId);
        int orderAmount = 0;
        for (OrderProduct orderProduct : orderProducts) {
            orderAmount += orderProduct.getAmount();
        }

        return orderAmount;
    }

}

