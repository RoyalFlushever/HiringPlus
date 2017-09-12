CREATE TABLE IF NOT EXISTS recommendations
(
    job_id bigint,
    rec_0 bigint,
    rec_1 bigint,
    rec_2 bigint,
    rec_3 bigint,
    rec_4 bigint
);

CREATE TABLE IF NOT EXISTS job_descriptions
(
    id bigint NOT NULL,
    job_title varchar (255),
    skill varchar (255)
);