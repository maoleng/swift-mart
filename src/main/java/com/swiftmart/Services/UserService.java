package com.swiftmart.Services;

import com.swiftmart.Enums.UserRole;
import com.swiftmart.Enums.UserStatus;
import com.swiftmart.Http.Requests.CreatePasswordRequest;
import com.swiftmart.Http.Requests.LoginRequest;
import com.swiftmart.Http.Requests.RegisterRequest;
import com.swiftmart.Http.Requests.UserRequest;
import com.swiftmart.Models.Location;
import com.swiftmart.Models.User;
import com.swiftmart.Repositories.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public void createPassword(User user, CreatePasswordRequest request)
    {
        user.setPassword(pe.encode(request.getPassword()));
        user.setStatus(UserStatus.ACTIVE.name());
        repository.getUserRepository().save(user);
    }

    public void create(UserRequest request)
    {
        User user = new User();
        user.setName(request.getName());
        String email = request.getEmail();
        user.setEmail(email);
        String username = email.split("@")[0];
        user.setUsername(username);
        user.setPassword(pe.encode(username));
        user.setRole(UserRole.SALE.name());
        user.setAvatar("https://cdn-icons-png.flaticon.com/512/5853/5853761.png");
        user.setStatus(UserStatus.INACTIVE.name());
        user.setSentAt(new Date());
        user.setCreatedAt(new Date());
        repository.getUserRepository().save(user);
    }

    public void update(User user)
    {
        repository.getUserRepository().save(user);
    }

}
