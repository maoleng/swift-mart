package com.swiftmart.Repositories;

import com.swiftmart.Models.ImportProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportProductRepository extends MongoRepository<ImportProduct, String>
{

    List<ImportProduct> getImportProductsByProductId(String productId);


}