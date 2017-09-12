package com.tdt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdt.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findByEmail(String email);
    
    Optional<User> findById(Long id);

    @Modifying
    @Query("delete from User u where u.email=:email")
    void deleteByEmail(@Param("email") String email);

    User findByResetToken(String token);
}
