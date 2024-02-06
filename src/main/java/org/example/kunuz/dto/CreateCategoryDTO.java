package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCategoryDTO {
    private Long orderNumber;
    @NotBlank(message = "NameUz field must has a value")
    @Size(min = 5,max = 200, message = "nameUz must be between 5 and 200 characters")
    private String nameUz;
    @Size(min = 5,max = 200, message = "nameRu must be between 5 and 200 characters")
    @NotBlank(message = "NameRu field must has a value")
    private String nameRu;
    @NotBlank(message = "NameEn field must has a value")
    @Size(min = 5,max = 200, message = "nameEn must be between 5 and 200 characters")
    private String nameEn;
}