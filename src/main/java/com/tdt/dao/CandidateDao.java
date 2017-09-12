package com.tdt.dao;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdt.model.Candidate;

@Repository
public interface CandidateDao extends JpaRepository<Candidate, Long> {
    
    Optional<Candidate> findById(Long candidateId);
    Set<Candidate> findByUserIdOrderByUpdatedAtDesc(Long userId);

    Page<Candidate> findByUserIdOrderByUpdatedAtDesc(Long userId, Pageable pageable);

    Set<Candidate> findByAccountIdOrderByUpdatedAtDesc(Long accountId);

    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.notes WHERE c.id = (:id)")
    Candidate findByIdAndFetchNotesEagerly(@Param("id") Long id);

    int countByUpdatedAtAfter(Date date);

}
