package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findAllByRoles(String roles);

    User findByEmail(String email);

    Long countByEmail(String email);

    Long countByUsername(String username);
}