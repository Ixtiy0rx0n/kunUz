package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    protected Integer id;
    @NotBlank(message = "NameUz field must has a value")
    @Size(min = 5,max = 200, message = "nameUz must be between 5 and 200 characters")
    private String nameUz;
    @NotBlank(message = "NameRu field must has a value")
    @Size(min = 5,max = 200, message = "nameRu must be between 5 and 200 characters")
    private String nameRu;
    @NotBlank(message = "NameEn field must has a value")
    @Size(min = 5,max = 200, message = "nameEn must be between 5 and 200 characters")
    private String nameEn;
    private Integer orderNumber;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    private Boolean visible;
}
