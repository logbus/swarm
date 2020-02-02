package com.swarm.backend.control;

import com.swarm.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
    @Modifying
    @Transactional
    @Query(value = "DROP TABLE IF EXISTS users", nativeQuery = true)
    void dropUsersTable();

    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE users (last_name VARCHAR(50) PRIMARY KEY, first_name VARCHAR (50))", nativeQuery = true)
    void createUsersTable();
}
