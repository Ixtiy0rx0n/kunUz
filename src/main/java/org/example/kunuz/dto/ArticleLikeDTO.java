package org.example.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleLikeDTO {
    private Integer id;
    private ProfileDTO profileId;
    private ArticleCreateDTO articleId;
    private LocalDateTime createdDate;


}
