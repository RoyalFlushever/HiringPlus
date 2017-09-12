package com.tdt.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdt.model.Job;

@Repository
public interface JobDao extends JpaRepository<Job, Long>{
    Optional<Job> findById(Long jobId);
    Set<Job> findByAccountIdOrderByUpdatedAtDesc(Long accountId);
}
