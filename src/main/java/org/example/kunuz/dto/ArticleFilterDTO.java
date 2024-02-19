package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.kunuz.enums.ArticleStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFilterDTO {
    private String id;
    private String title;
    private Integer regionId;
    private Integer categoryId;
    private LocalDate createdDate;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate publishedDate;
    private Integer moderatorId;
    private Integer publisherId;
    private ArticleStatus status;
}
