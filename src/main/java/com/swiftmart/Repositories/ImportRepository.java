package com.swiftmart.Repositories;

import com.swiftmart.Models.Import;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportRepository extends MongoRepository<Import, String>
{

}