package com.swarm.backend.control;

import com.swarm.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


@Component
public class Initializer {
    @Autowired
    UsersRepository usersRepository;

    @PostConstruct
    @Transactional
    public void initializeDB() {
        System.out.println("----- initializeDB() -----");
        usersRepository.dropUsersTable();
        usersRepository.createUsersTable();
        for (int i = 1; i <= 1000; i++) {
            usersRepository.save(new User("lastName" + i, "firstName" + i));
        }
        System.out.println("----- initializeDB() done -----");
    }
}
