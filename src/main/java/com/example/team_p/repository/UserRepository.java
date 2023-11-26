package com.example.team_p.repository;

import com.example.team_p.entity.User;
import com.example.team_p.repository.mapping.UserInfoMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUsername(String username);
    Optional<User> findByPassword(String password);
}
