package org.example.kunuz.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttachDTO {
    private String id;
    @NotBlank(message = "OriginalName field must has a value")
    private String originalName;
    private String path;
    private Long size;
    private String extension;
    private LocalDateTime createdData;
    private String url;
}
