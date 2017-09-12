package com.tdt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="recommendations")
public class Recommendations implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -4054541013854057749L;

    @Id
    @Column(name="job_id")
    private Long jobId;

    @OneToOne
    @JoinColumn(name="job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name="rec_0")
    private Candidate candidate0;

    @ManyToOne
    @JoinColumn(name="rec_1")
    private Candidate candidate1;

    @ManyToOne
    @JoinColumn(name="rec_2")
    private Candidate candidate2;

    @ManyToOne
    @JoinColumn(name="rec_3")
    private Candidate candidate3;

    @ManyToOne
    @JoinColumn(name="rec_4")
    private Candidate candidate4;
}
