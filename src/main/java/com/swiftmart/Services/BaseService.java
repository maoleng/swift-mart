package com.swiftmart.Services;

import com.swiftmart.Repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class BaseService
{

    protected final Repository repository;

    @Autowired
    public BaseService(Repository repository) {
        this.repository = repository;
    }


}
