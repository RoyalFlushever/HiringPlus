package com.hiringplus.exception;

public class CandidateNotFoundException extends RuntimeException {
    
    public CandidateNotFoundException(Long candidateId) {
        super("Could not found candidate with id '" + candidateId +"'.");
    }

}
