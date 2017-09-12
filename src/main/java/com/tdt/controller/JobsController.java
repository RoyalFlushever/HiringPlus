package com.tdt.controller;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.tdt.model.Skill;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tdt.dao.JobCandidateDao;
import com.tdt.dao.JobDao;
import com.tdt.dao.JobDescriptionDao;
import com.tdt.model.Candidate;
import com.tdt.model.Job;
import com.tdt.model.User;

@RestController
public class JobsController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(JobsController.class);

    @Autowired
    JobDao jobDao;

    @Autowired
    JobCandidateDao jobCandidateDao;
    
    @Autowired
    JobDescriptionDao jobDescriptionDao;

    @RequestMapping(method = RequestMethod.GET, value = "/account/{accountId}/jobs", produces = "application/json")
    public Set<Job> findByAccountId(@PathVariable Long accountId) {
        
        LOGGER.debug("Find jobs by account id " + accountId);
        findAccount(accountId);
        return jobDao.findByAccountIdOrderByUpdatedAtDesc(accountId);
        
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/{userId}/job", produces = "application/json")
    public boolean save(@PathVariable Long userId, @RequestBody Job job) {
        LOGGER.debug("User id " + userId + " is saving a job.");

        User user = findUser(userId);
        job.setUserId(user.getId());
        job.setAccountId(user.getAccount().getId());
        job.setCreatedAt(new Date(new java.util.Date().getTime()));
        job.setUpdatedAt(new Date(new java.util.Date().getTime()));

        List<Skill> existingSkills = findExistingSkillsByNames(job.getSkills());

        job.setSkills(
                job
                .getSkills()
                .stream()
                .map(skill -> findSkillByName(existingSkills, skill.getName()).orElse(skill))
                .collect(Collectors.toSet())
        );

        if (job.getSkills() != null) {
            job.getSkills().forEach(s -> {
                if (s.getJobs() == null) {
                    s.setJobs(new HashSet<>());
                }
                s.getJobs().add(job);
            });
        }

        jobDao.save(job);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/account/{accountId}/job/{jobId}", produces = "application/json")
    public Job findById(@PathVariable Long accountId, @PathVariable Long jobId) {

        LOGGER.debug("Find a job with accountId " + accountId + " and jobId " + jobId);

        findAccount(accountId);
        return jobDao.findOne(jobId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/job/{jobId}/candidates", produces = "application/json")
    public Set<Candidate> findCandidatesByJobId(@PathVariable Long jobId) {
        Job job = jobDao.findOne(jobId);
        return job.getCandidates();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/job/{jobId}", produces = "application/json")
    public Job updateById(@PathVariable Long jobId, @RequestBody Job job) {
        LOGGER.debug("Update the job with id " + jobId);
        findJob(jobId);
        job.setId(jobId);
        return jobDao.save(job);
    }

}
