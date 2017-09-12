package com.tdt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Job_Candidate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JobCandidate implements java.io.Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 7797647774961346465L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name="account_id", nullable = false)
    protected Long accountId;
    
    @Column(name="job_id", nullable = false)
    protected Long jobId;
    
    @Column(name="candidate_id", nullable = false)
    protected Long candidateId;
    
    @Column(name="created_at", nullable = false)
    protected Date createdAt;
    
}
