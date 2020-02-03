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
import java.util.*;

import static java.util.Collections.singletonList;

@RestController
public class BackendRestController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/users")
    public User getUsers(@RequestHeader HttpHeaders headers) {
        int randomNr = (int) Math.min(1 + (Math.random() * 100), 100);
        Optional<User> user = usersRepository.findById("lastName" + randomNr);
        User result = user.orElseGet(() -> new User("Not Found", "Not Found", "", "", ""));
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            result.setBackend(inetAddress.getHostName() + " (" + inetAddress.getHostAddress() + ")");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        result.setClient(headers.getOrDefault("host", singletonList("header 'host' not set")).get(0) +
                " (" + headers.getOrDefault("nginx_client_addr", singletonList("header 'nginx_client_addr not set")).get(0) + ")");
        result.setCaller(headers.getOrDefault("nginx_hostname", singletonList("header 'nginx_hostname' not set")).get(0) +
                " (" + headers.getOrDefault("nginx_server_addr", singletonList("header 'nginx_server_addr' not set")).get(0) + ")");
        return result;
    }
}
