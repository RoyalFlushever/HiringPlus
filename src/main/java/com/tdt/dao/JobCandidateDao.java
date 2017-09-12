package com.tdt.dao;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdt.model.JobCandidate;

public interface JobCandidateDao extends JpaRepository<JobCandidate,Long>{
    
    JobCandidate findByCandidateIdAndJobId(Long candidateId, Long jobId);
    
    @Transactional
    Long removeByCandidateIdAndJobId(Long candidateId, Long jobId);
}
