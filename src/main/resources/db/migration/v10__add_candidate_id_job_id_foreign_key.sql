ALTER TABLE recommendations
    ADD FOREIGN KEY (rec_0)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;

ALTER TABLE recommendations
    ADD FOREIGN KEY (rec_1)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;

ALTER TABLE recommendations
    ADD FOREIGN KEY (rec_2)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;

ALTER TABLE recommendations
    ADD FOREIGN KEY (rec_3)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;

ALTER TABLE recommendations
    ADD FOREIGN KEY (rec_4)
    REFERENCES Candidates(id)
    MATCH SIMPLE
;

ALTER TABLE recommendations
    ADD FOREIGN KEY (job_id)
    REFERENCES Jobs(id)
    MATCH SIMPLE
;
