CREATE TABLE IF NOT EXISTS Jobs
(
    id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    job_title VARCHAR (255),
    department VARCHAR (255),
    status VARCHAR (25),
    location VARCHAR (255),
    remote VARCHAR (10),
    contract VARCHAR (10),
    visa VARCHAR (10),
    full_time VARCHAR (10),
    minimum_salary INT,
    maximum_salary INT,
    responsibilities VARCHAR,
    desired_skills VARCHAR,
    benefits VARCHAR,
    rounds_of_interviews INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Job_Candidate
(
    id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    candidate_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE Jobs
    ADD FOREIGN KEY (account_id)
    REFERENCES Accounts (id)
    MATCH SIMPLE
;

ALTER TABLE Jobs
    ADD FOREIGN KEY (user_id)
    REFERENCES Users(id)
    MATCH SIMPLE
;

ALTER TABLE Job_Candidate
    ADD FOREIGN KEY (account_id)
    REFERENCES Accounts(id)
    MATCH SIMPLE
;

ALTER TABLE Job_Candidate
    ADD FOREIGN KEY (job_id)
    REFERENCES Jobs(id)
    MATCH SIMPLE
;

ALTER TABLE Job_Candidate
    ADD FOREIGN KEY (candidate_id)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;