package com.swiftmart.Services;

import com.swiftmart.Repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

abstract public class BaseService
{

    public final Repository repository;

    @Autowired
    public MongoTemplate mongoTemplate;

    @Autowired
    public BaseService(Repository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }


}
