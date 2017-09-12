-- Add your scripts here
CREATE TABLE IF NOT EXISTS Accounts
(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Users
(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  email VARCHAR(255),
  password VARCHAR(255),
  reset_token VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  account_id BIGINT
);

-- Create schemas

-- Create tables
CREATE TABLE IF NOT EXISTS Candidates
(
    id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    name VARCHAR(256) NOT NULL,
    location VARCHAR(256) DEFAULT 'Not available',
    email VARCHAR(256),
    phone VARCHAR(25),
    website VARCHAR(256),
    desired_salary VARCHAR(256),
    is_visa_required BOOLEAN,
    visa_type VARCHAR(256),
    visa_notes VARCHAR(256),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    linkedin_profile VARCHAR(256),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Candidate_Contact
(
    id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    type VARCHAR(256),
    value VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS Experience_Detail
(
    id BIGINT NOT NULL,
    candidate_id BIGINT NOT NULL,
    company_name VARCHAR(256) NOT NULL,
    title VARCHAR(256) NOT NULL,
    location VARCHAR(256),
    time_period VARCHAR(80),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Education_Detail
(
    id BIGINT NOT NULL,
    candidate_id BIGINT NOT NULL,
    school VARCHAR(256) NOT NULL,
    dates_attended VARCHAR(40),
    fields_of_study VARCHAR(256),
    degree VARCHAR(256),
    name_of_course VARCHAR(256),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Skills
(
    id BIGINT NOT NULL,
    name VARCHAR(256) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Companies
(
    id BIGINT NOT NULL,
    name VARCHAR(256) NOT NULL,
    industry VARCHAR(256),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Candidate_Skill
(
    candidate_id BIGINT NOT NULL,
    skill_id BIGINT
);

CREATE TABLE IF NOT EXISTS Notes
(
    id BIGSERIAL NOT NULL,
    candidate_id BIGINT NOT NULL,
    content VARCHAR(256) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    created_by_id BIGINT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Referrals
(
    id BIGINT NOT NULL,
    candidate_id BIGINT,
    company_id BIGINT,
    name VARCHAR(256),
    position VARCHAR(256),
    time_period VARCHAR(80),
    PRIMARY KEY(id)
);


-- Create FKs
ALTER TABLE Candidate_Skill
    ADD    FOREIGN KEY (skill_id)
    REFERENCES Skills(id)
    MATCH SIMPLE
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
    
ALTER TABLE Candidate_Skill
    ADD    FOREIGN KEY (candidate_id)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;
    
ALTER TABLE Notes
    ADD    FOREIGN KEY (candidate_id)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;

ALTER TABLE Notes
    ADD    FOREIGN KEY (created_by_id)
    REFERENCES Users(id)
    MATCH SIMPLE
;

ALTER TABLE Experience_Detail
    ADD    FOREIGN KEY (candidate_id)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;
    
ALTER TABLE Education_Detail
    ADD    FOREIGN KEY (candidate_id)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;
    
ALTER TABLE Referrals
    ADD    FOREIGN KEY (candidate_id)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;
    
ALTER TABLE Referrals
    ADD    FOREIGN KEY (company_id)
    REFERENCES Companies(id)
    MATCH SIMPLE
;
    

-- Create Indexes
CREATE INDEX candidate_index ON Candidates (id);
CREATE UNIQUE INDEX uk_6dotkott2kjsp8vw4d0m25fb7 ON Users (email);
CREATE UNIQUE INDEX uk_kpeyao30ym7l5vf8wsterwase ON Users (reset_token);


-- Create Sequences

CREATE SEQUENCE hibernate_sequence START 1;