package com.swiftmart.Services;

import com.swiftmart.Http.Requests.LoginRequest;
import com.swiftmart.Http.Requests.RegisterRequest;
import com.swiftmart.Models.Location;
import com.swiftmart.Models.User;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class UserService extends BaseService
{

    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

    public UserService(Repository repository, MongoTemplate mongoTemplate) {
        super(repository, mongoTemplate);
    }

    public List<User> getUsersByLocationName(String locationName) {
        Location location = repository.getLocationRepository().findByAddress(locationName);
        return repository.getUserRepository().findByLocationId(location.get_id());
    }

    public List<User> getUsersWithLocations() {
        Query query = new Query();
        List<User> users = mongoTemplate.find(query, User.class);

        for (User user : users) {
            String locationId = user.getLocationId();
            if (locationId != null) {
                Location location = mongoTemplate.findById(locationId, Location.class);
                user.setLocation(location);
            }
        }

        return users;
    }

    public User authenticate(LoginRequest request)
    {
        User user = repository.getUserRepository().findByUsername(request.getUsername());

        if (user != null && pe.matches(request.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }

    public void saveUser(RegisterRequest request)
    {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(pe.encode(request.getPassword()));
        repository.getUserRepository().save(user);
    }
}
