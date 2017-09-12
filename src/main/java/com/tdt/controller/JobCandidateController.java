package com.tdt.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tdt.dao.JobCandidateDao;
import com.tdt.model.JobCandidate;

@RestController
public class JobCandidateController extends AbstractController {

    @Autowired
    JobCandidateDao jobCandidateDao;

    @RequestMapping(method = RequestMethod.POST, value = "account/{accountId}/jobCandidate", produces = "application/json")
    public JobCandidate save(@PathVariable Long accountId, @RequestBody JobCandidate jobCandidate) {

        findAccount(accountId);
        findJob(jobCandidate.getJobId());
        findCandidate(jobCandidate.getCandidateId());

        jobCandidate.setAccountId(accountId);
        jobCandidate.setCreatedAt(new Date(new java.util.Date().getTime()));

        return jobCandidateDao.save(jobCandidate);

    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "candidate/{candidateId}/job/{jobId}", produces = "application/json")
    public JobCandidate delete(@PathVariable Long candidateId, @PathVariable Long jobId) {
        JobCandidate jobCandidate = jobCandidateDao.findByCandidateIdAndJobId(candidateId, jobId);
        jobCandidateDao.removeByCandidateIdAndJobId(candidateId, jobId);
        return jobCandidate;
    }

}
