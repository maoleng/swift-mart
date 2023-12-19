package com.swiftmart.Repositories;

import com.swiftmart.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>
{

    Product findBy_id(String id);
    Product findByName(String name);

}