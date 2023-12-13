package com.swiftmart.Repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class Repository
{

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public Repository(
        UserRepository userRepository,
        LocationRepository locationRepository
    ) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

}
