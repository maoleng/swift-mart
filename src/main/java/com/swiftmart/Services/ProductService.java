package com.swiftmart.Services;

import com.swiftmart.Http.Requests.ProductRequest;
import com.swiftmart.Http.Requests.UserRequest;
import com.swiftmart.Models.Category;
import com.swiftmart.Models.Product;
import com.swiftmart.Models.Samples.ImportProductInfo;
import com.swiftmart.Models.User;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@org.springframework.stereotype.Service
public class ProductService extends BaseService
{

    private final ImportProductService importProductService;
    private final OrderProductService orderProductService;

    public ProductService(
        Repository repository, MongoTemplate mongoTemplate,
        ImportProductService importProductService,
        OrderProductService orderProductService
    ) {
        super(repository, mongoTemplate);
        this.importProductService = importProductService;
        this.orderProductService = orderProductService;
    }

    public List<Product> getProductsWithInfo() {
        Query query = new Query();
        List<Product> products = mongoTemplate.find(query, Product.class);

        for (Product product : products) {
            if (product.getCategoryId() != null) {
                product.setCategory(mongoTemplate.findById(product.getCategoryId(), Category.class));
            }

            ImportProductInfo importProductInfo = importProductService.getImportedProductInfo(product.get_id());
            int importedAmount = importProductInfo.getImportedAmount();
            int orderedAmount = orderProductService.sumOrderedProduct(product.get_id());
            product.setQuantity(importedAmount - orderedAmount);

            product.setImportPrice(importProductInfo.getImportPrice());
        }

        return products;
    }

    public Product findBy_id(String id)
    {
        return repository.getProductRepository().findBy_id(id);
    }

    public void update(String id, ProductRequest request)
    {
        Product product = findBy_id(id);
        product.setName(request.getName());
        product.setImage(request.getImage());
        product.setPrice(Double.parseDouble(request.getPrice()));
        product.setDescription(request.getDescription());
        product.setCategoryId(request.getCategory());

        repository.getProductRepository().save(product);
    }

    public void create(ProductRequest request)
    {
        Product product = new Product();

        product.setName(request.getName());
        product.setImage(request.getImage());
        product.setPrice(Double.parseDouble(request.getPrice()));
        product.setDescription(request.getDescription());
        product.setCategoryId(request.getCategory());

        repository.getProductRepository().save(product);
    }

    public boolean destroy(String id) {
        boolean canDestroy = repository.getImportProductRepository().getImportProductsByProductId(id).isEmpty() &&
                repository.getOrderProductRepository().getOrderProductsByProductId(id).isEmpty();

        if (canDestroy) {
            repository.getProductRepository().delete(findBy_id(id));
        }

        return canDestroy;
    }


}

