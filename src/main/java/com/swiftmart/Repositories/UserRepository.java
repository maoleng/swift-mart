package com.swiftmart.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.swiftmart.Models.User;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{

    List<User> findByLocationId(String id);

}
