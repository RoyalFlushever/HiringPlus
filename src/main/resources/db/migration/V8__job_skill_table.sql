ALTER table Jobs
    DROP COLUMN desired_skills;

CREATE TABLE IF NOT EXISTS Job_Skill
(
    job_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL
);