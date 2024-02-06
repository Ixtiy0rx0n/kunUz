package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDTO {
    @Size(min = 5,max = 200, message = "name must be between 5 and 200 characters")
    private String name;
    @Size(min = 5,max = 200, message = "Surname must be between 5 and 200 characters")
    private String surname;
    @Email(message = "Email should be valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Password field must has a value")
    private String password;
    private String phone;
}
