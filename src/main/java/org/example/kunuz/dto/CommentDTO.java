package org.example.kunuz.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    @NotNull(message = "profile id required")
    private Integer profileId;
    @NotBlank(message = "content required")
    @Size(min = 1, max = 1024, message = "content is required")
    private String content;
    @NotNull(message = "article id required")
    private String articleId;
    private Boolean visible=true;
}
