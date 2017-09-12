ALTER TABLE candidate_skill
    ADD PRIMARY KEY (candidate_id, skill_id);

ALTER TABLE job_candidate
    ADD CONSTRAINT jobcandidate_unique UNIQUE (job_id, candidate_id);
