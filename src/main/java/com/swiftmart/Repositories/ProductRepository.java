package com.swiftmart.Repositories;

import com.swiftmart.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>
{

    Product findBy_id(String id);

}