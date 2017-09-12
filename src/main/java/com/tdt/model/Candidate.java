package com.tdt.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Candidates")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id" )
public class Candidate implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1903981549255716056L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected Long userId;
    
    @Column(name="account_id", nullable = false)
    protected Long accountId;

    @Column(nullable = false)
    @NotNull
    protected String name;

    @Column(nullable = true)
    protected String location;

    @Column(nullable = true)
    protected String phone;

    @Column(nullable = true)
    protected String email;

    @Column(nullable = true)
    protected String website;

    @Column(name="desired_salary", nullable = true)
    protected String desiredSalary;

    @Column(name="is_visa_required", nullable = true)
    protected Boolean isVisaRequired;

    @Column(name="visa_type", nullable = true)
    protected String visaType;

    @Column(name="visa_notes", nullable = true)
    protected String visaNotes;

    @Column(name="created_at", nullable = false)
    protected Date createdAt;

    @Column(name="updated_at", nullable = false)
    protected Date updatedAt;

    @Column(name="linkedin_profile", nullable = true)
    protected String linkedinProfile;

    @Column(name="resume_url", nullable = true)
    protected String resumeUrl;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "candidate")
    @OrderBy("id")
    protected Set<ExperienceDetail> experienceDetails = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "candidate")
    @OrderBy("id")
    protected Set<EducationDetail> educationDetails = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "candidates")
    protected Set<Skill> skills;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "candidate")
    @OrderBy("id DESC")
    protected Set<Note> notes = new HashSet<>();

    @OneToOne(mappedBy = "candidate")
    private Reminder reminder;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "job_candidate",
            joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"))
    protected Set<Job> jobs;
}
