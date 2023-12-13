package com.swiftmart.Services;

import com.swiftmart.Models.Location;
import com.swiftmart.Models.User;
import com.swiftmart.Repositories.Repository;
import com.swiftmart.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService
{

    public UserService(Repository repository) {
        super(repository);
    }

    public List<User> getUsersByLocationName(String locationName) {
        Location location = repository.getLocationRepository().findByAddress(locationName);
        return repository.getUserRepository().findByLocationId(location.get_id());
    }

    public void saveUser(User user) {
        repository.getUserRepository().save(user);
    }
}
