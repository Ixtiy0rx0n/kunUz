package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleShortInfoDTO {
    private String id;
    private String title;
    private String description;
    private AttachDTO image;
    private LocalDateTime publishedDate;
}
