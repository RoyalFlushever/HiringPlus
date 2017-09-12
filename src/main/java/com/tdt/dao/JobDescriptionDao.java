package com.tdt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdt.model.JobDescription;

public interface JobDescriptionDao extends JpaRepository<JobDescription, Long> {

}
