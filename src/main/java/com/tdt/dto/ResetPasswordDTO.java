package com.tdt.dto;

import com.tdt.validation.Matches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Matches(fields = "password",
        verifyFields = "repassword",
        message = "new password and confirm does not math")
public class ResetPasswordDTO {

    @NotBlank
    protected String token;
    @Size(min = 6, max = 100)
    protected String password;
    protected String repassword;
}
