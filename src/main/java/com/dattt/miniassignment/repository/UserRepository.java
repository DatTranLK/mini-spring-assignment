package com.dattt.miniassignment.repository;

import com.dattt.miniassignment.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsernameAndPassword(String username, String password);
}
