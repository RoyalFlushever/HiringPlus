package com.tdt.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Skills")
@Getter
@Setter
@JsonIgnoreProperties({ "candidates", "jobs" })
public class Skill implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6469321427098390740L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(nullable = false)
    protected String name;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "candidate_skill",
            joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"))
    protected Set<Candidate> candidates;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "job_skill",
            joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"))
    protected Set<Job> jobs;
}
