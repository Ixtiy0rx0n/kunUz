package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private Integer id;
    @NotBlank(message = "NameUz must be between 10 and 200 characters")
    @Size(min = 10,max = 200, message = "NameUz must be between 10 and 200 characters")
    private String nameUz;

    @NotBlank(message = "nameRu must be between 10 and 200 characters")
    @Size(min = 10,max = 200, message = "nameRu must be between 10 and 200 characters")
    private String nameRu;

    @NotBlank(message = "nameEn; must be between 10 and 200 characters")
    @Size(min = 10,max = 200, message = "nameEn; must be between 10 and 200 characters")
    private String nameEn;

    @NotNull(message = "orderNumber required")
    private Integer orderNumber;

    private String name;

    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    private Boolean visible;
}
