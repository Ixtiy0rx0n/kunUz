package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.enums.ProfileStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    protected Integer id;
    @NotBlank(message = "Name field must has a value")
    private String name;
    @NotBlank(message = "Surname field must has a value")
    private String surname;
    @Email(message = "Email should be valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Password field must has a value")
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    @PastOrPresent(message = "CreatedDate field must has a value")
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    private Boolean visible;
    private String jwt;
}
