INSERT INTO Accounts (id, name) values (1, 'test');

UPDATE Users SET account_id=1 where id=0;

ALTER TABLE Candidates
 ADD COLUMN account_id BIGINT NOT NULL DEFAULT 1;
 
ALTER TABLE Candidates
    ADD FOREIGN KEY (account_id)
    REFERENCES Accounts(id)
    MATCH SIMPLE
;