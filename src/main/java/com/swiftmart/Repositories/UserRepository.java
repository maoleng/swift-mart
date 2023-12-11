package com.swiftmart.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.swiftmart.Models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{

}