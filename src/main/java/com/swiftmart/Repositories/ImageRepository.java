package com.swiftmart.Repositories;

import com.swiftmart.Models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String>
{

}