CREATE TABLE IF NOT EXISTS reminders
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    candidate_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    reminder_date TIMESTAMPTZ NOT NULL,
    title varchar(255)
);

ALTER TABLE reminders
    ADD FOREIGN KEY (candidate_id)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;

ALTER TABLE reminders
    ADD FOREIGN KEY (user_id)
    REFERENCES Users(id)
    MATCH SIMPLE
;
