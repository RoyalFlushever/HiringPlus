package com.tdt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tdt.dto.CandidateDTO;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Entity
@Table(name = "Jobs")
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Job implements java.io.Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4548962211307009320L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    @JsonIgnore
    @Column(name="user_id", nullable = false)
    protected Long userId;
    
    @Column(name="account_id", nullable = false)
    protected Long accountId;
    
    @Column(name="job_title",nullable = false)
    @NotNull
    protected String jobTitle;
    
    @Column(nullable = true)
    protected String department;
    
    @Column(nullable = true)
    protected String location;
    
    @Column(nullable = true)
    protected String remote;
    
    @Column(nullable = true)
    protected String contract;
    
    @Column(nullable = true)
    protected String visa;
    
    @Column(nullable = true)
    protected String fullTime;
    
    @Column(name="minimum_salary", nullable = true)
    @Setter(AccessLevel.NONE)
    private Integer minSalary;
    
    @Column(name="maximum_salary", nullable = true)
    @Setter(AccessLevel.NONE)
    private Integer maxSalary;
    
    @Column(nullable = true)
    protected String responsibilities;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jobs")
    protected Set<Skill> skills;
    
    @Column(nullable = true)
    protected String benefits;
    
    @Column(name="rounds_of_interviews", nullable = true)
    protected Integer roundsOfInterviews;
    
    @Column(name="created_at", nullable = false)
    protected Date createdAt;
    
    @Column(name="updated_at", nullable = false)
    protected Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jobs")
    @JsonIgnore
    protected Set<Candidate> candidates;

    @JsonIgnore
    @OneToOne
    @PrimaryKeyJoinColumn
    protected Recommendations recommendations;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public List<CandidateDTO> getShortlistedCandidates() {
        return getCandidates()
                .stream()
                .map(candidate -> new CandidateDTO(candidate.getId(),
                                                   candidate.getName(),
                                                   candidate.getPhone(),
                                                   candidate.getEmail(),
                                                   candidate.getLocation(),
                                                   candidate.getResumeUrl()))
                .collect(Collectors.toList());
    }

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public List<CandidateDTO> getRecommendedCandidates() {
        if (getRecommendations() == null) {
            return new ArrayList<CandidateDTO>();
        }

        val recommendedCandidates = new ArrayList<Candidate>();

        if (getRecommendations().getCandidate0() != null) {
            recommendedCandidates.add(getRecommendations().getCandidate0());
        }
        if (getRecommendations().getCandidate1() != null) {
            recommendedCandidates.add(getRecommendations().getCandidate1());
        }
        if (getRecommendations().getCandidate2() != null) {
            recommendedCandidates.add(getRecommendations().getCandidate2());
        }
        if (getRecommendations().getCandidate3() != null) {
            recommendedCandidates.add(getRecommendations().getCandidate3());
        }
        if (getRecommendations().getCandidate4() != null) {
            recommendedCandidates.add(getRecommendations().getCandidate4());
        }

        return recommendedCandidates
                .stream()
                .map(candidate -> new CandidateDTO(candidate.getId(),
                        candidate.getName(),
                        candidate.getPhone(),
                        candidate.getEmail(),
                        candidate.getLocation(),
                        candidate.getResumeUrl()))
                .collect(Collectors.toList());
    }
}
