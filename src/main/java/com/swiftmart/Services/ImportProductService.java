package com.swiftmart.Services;

import ch.qos.logback.core.joran.sanity.Pair;
import com.swiftmart.Models.ImportProduct;
import com.swiftmart.Models.Samples.ImportProductInfo;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImportProductService extends BaseService
{

    public ImportProductService(Repository repository, MongoTemplate mongoTemplate) {
        super(repository, mongoTemplate);
    }

    public int sumImportedProduct(List<ImportProduct> importProducts)
    {
        int importAmount = 0;
        for (ImportProduct importProduct : importProducts) {
            importAmount += importProduct.getAmount();
        }

        return importAmount;
    }

    public ImportProductInfo getImportedProductInfo(String productId)
    {
        List<ImportProduct> importProducts = repository.getImportProductRepository().getImportProductsByProductId(productId);
        int importedAmount = 0;
        Double importPrice = 0.0;
        if (! importProducts.isEmpty()) {
            importedAmount = sumImportedProduct(importProducts);
            importPrice = importProducts.get(importProducts.size() - 1).getPrice();
        }

        return new ImportProductInfo(importPrice, importedAmount);
    }

}

