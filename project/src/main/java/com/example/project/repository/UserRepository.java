package com.example.project.repository;

import com.example.project.domain.User;
import com.example.project.dto.request.UserFormRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S entity);



}