package com.swiftmart.Services;

import com.swiftmart.Models.Category;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService
{

    public CategoryService(Repository repository, MongoTemplate mongoTemplate) {
        super(repository, mongoTemplate);
    }

    public List<Category> findAll()
    {
        return repository.getCategoryRepository().findAll();
    }

}

