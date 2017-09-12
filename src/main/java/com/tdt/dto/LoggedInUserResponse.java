package com.tdt.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LoggedInUserResponse implements DTO {

    protected Long id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected Long accountId;
}
