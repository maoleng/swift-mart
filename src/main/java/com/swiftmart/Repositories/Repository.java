package com.swiftmart.Repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class Repository
{

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;
    private final ImportRepository importRepository;
    private final OrderRepository orderRepository;
    private final ImportProductRepository importProductRepository;
    private final OrderProductRepository orderProductRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public Repository(
        UserRepository userRepository,
        LocationRepository locationRepository,
        ProductRepository productRepository,
        ImportRepository importRepository,
        OrderRepository orderRepository,
        ImportProductRepository importProductRepository,
        OrderProductRepository orderProductRepository,
        CategoryRepository categoryRepository
    ) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.productRepository = productRepository;
        this.importRepository = importRepository;
        this.orderRepository = orderRepository;
        this.importProductRepository = importProductRepository;
        this.orderProductRepository = orderProductRepository;
        this.categoryRepository = categoryRepository;
    }

}
