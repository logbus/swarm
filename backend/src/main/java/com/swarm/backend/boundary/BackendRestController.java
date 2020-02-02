package com.swarm.backend.boundary;

import com.swarm.backend.control.UsersRepository;
import com.swarm.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BackendRestController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/users")
    public User getUsers() {
        int randomNr = (int) Math.min(1 + (Math.random() * 100), 100);
        Optional<User> user = usersRepository.findById("lastName" + randomNr);
        return user.isPresent() ? user.get() : new User("Not Found", "Not Found");
    }
}
