package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.enums.ProfileStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDTO {
    protected Integer id;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private Integer orderNumber;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    private Boolean visible;
}
