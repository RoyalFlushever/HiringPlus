package com.hiringplus.exception;

public class JobNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 7136170001505543449L;

    public JobNotFoundException(Long jobId) {
        super("Could not found job with id '" + jobId +"'.");
    }
}
