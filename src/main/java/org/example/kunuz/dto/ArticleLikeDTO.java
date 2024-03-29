package org.example.kunuz.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.kunuz.enums.ArticleLikeStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleLikeDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "profile id required")
    private Integer profileId;
    @NotBlank(message = "article id required")
    private String articleId;
    private LocalDateTime createdDate;
    private ArticleLikeStatus status;

}
