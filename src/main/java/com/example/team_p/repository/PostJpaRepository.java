package com.example.team_p.repository;

import com.example.team_p.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostJpaRepository extends JpaRepository<PostEntity,Long>{
}