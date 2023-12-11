package com.swiftmart.Repositories;

import com.swiftmart.Models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, String>
{

}