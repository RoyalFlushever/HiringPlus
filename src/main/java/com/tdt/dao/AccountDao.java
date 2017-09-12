package com.tdt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdt.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{
    Optional<Account> findById(Long id);
}
