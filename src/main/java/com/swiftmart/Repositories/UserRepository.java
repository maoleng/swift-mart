package com.swiftmart.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.swiftmart.Models.User;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{

    List<User> findByLocationId(String id);
    List<User> findByRoleIn(List<String> role);
    User findByUsername(String username);
    User findBy_id(String id);
    User findByPhone(String phone);

}
