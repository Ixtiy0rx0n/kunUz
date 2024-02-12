package org.example.kunuz.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentLikeDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull(message = "profile id required")
    private Integer profileId;
    @NotNull(message = "created date required")
    private LocalDateTime createdDate;
    @NotNull(message = "comment id required")
    private Integer commentId;


}
