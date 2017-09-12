package com.tdt.dto;

import com.tdt.model.User;
import com.tdt.validation.Matches;
import com.tdt.validation.Unique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Matches(fields = "password",
        verifyFields = "repassword",
        message = "Password does not matches confirm")
public class UserDTO {

    @Email
    @Unique(clazz = User.class, name = "email", message = "Email must be unique")
    @NotBlank
    protected String email;
    @Size(min = 6, max = 100)
    protected String password;
    protected String repassword;
    @Column(name = "first_name")
    protected String firstname;
    @Column(name = "last_name")
    protected String lastname;
}
