package com.example.team_p.repository;

import com.example.team_p.entity.Post;
import com.example.team_p.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreateDateDesc();

//    Optional<User> findByUserId(String userId);
}
