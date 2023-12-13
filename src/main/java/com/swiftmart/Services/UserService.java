package com.swiftmart.Services;

import com.swiftmart.Http.Requests.LoginRequest;
import com.swiftmart.Http.Requests.RegisterRequest;
import com.swiftmart.Models.Location;
import com.swiftmart.Models.User;
import com.swiftmart.Repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService
{

    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

    public UserService(Repository repository) {
        super(repository);
    }

    public List<User> getUsersByLocationName(String locationName) {
        Location location = repository.getLocationRepository().findByAddress(locationName);
        return repository.getUserRepository().findByLocationId(location.get_id());
    }

    public boolean authenticate(LoginRequest request)
    {
        User user = repository.getUserRepository().findByUsername(request.getUsername());

        return user != null && !pe.matches(user.getPassword(), request.getPassword());
    }

    public void saveUser(RegisterRequest request)
    {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(pe.encode(request.getPassword()));
        repository.getUserRepository().save(user);
    }
}
