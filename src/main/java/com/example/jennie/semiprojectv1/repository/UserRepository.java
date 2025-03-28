package com.example.jennie.semiprojectv1.repository;

import com.example.jennie.semiprojectv1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean findByEmail(String email);

    Optional<User> findByUserid(String username);
}
