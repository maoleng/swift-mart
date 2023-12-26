package com.swiftmart.Services;

import com.swiftmart.Models.OrderProduct;
import com.swiftmart.Models.Product;
import com.swiftmart.Models.Samples.CartProductInfo;
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

    public List<OrderProduct> getOrderProductsByOrderId(String orderId)
    {
        return repository.getOrderProductRepository().getOrderProductsByOrderId(orderId);
    }

    public void insert(List<CartProductInfo> cartProducts, String orderId)
    {
        for (CartProductInfo cartProductInfo : cartProducts) {
            Product product = cartProductInfo.getProduct();

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductId(product.get_id());
            orderProduct.setName(product.getName());
            orderProduct.setPrice(product.getPrice());
            orderProduct.setOrderId(orderId);
            orderProduct.setAmount(cartProductInfo.getQuantity());

            repository.getOrderProductRepository().save(orderProduct);
        }
    }

}

