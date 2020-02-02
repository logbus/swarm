package com.swarm.backend.boundary;

import com.swarm.backend.control.UsersRepository;
import com.swarm.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@RestController
public class BackendRestController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/users")
    public User getUsers(@RequestHeader HttpHeaders headers) {
        int randomNr = (int) Math.min(1 + (Math.random() * 100), 100);
        Optional<User> user = usersRepository.findById("lastName" + randomNr);
        User result = user.isPresent() ? user.get() : new User("Not Found", "Not Found", "", "");
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            result.setBackend(inetAddress.getHostAddress() + " (" + inetAddress.getHostName() + ")");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String caller = "(" + headers.getHost().getHostName() + ")";
        InetAddress callerAddress = headers.getHost().getAddress();
        if (callerAddress != null) {
            caller = callerAddress.getHostAddress() + " " + caller;
        }
        result.setCaller(caller);
        return result;
    }
}
