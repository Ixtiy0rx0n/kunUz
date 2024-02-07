package org.example.kunuz.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleCreateDTO {
    @NotBlank(message = "title field must has a value")
    @Size(min = 5,max = 200, message = "title must be between 5 and 200 characters")
    private String title;

    @NotBlank(message = "description field must has a value")
    @Size(min = 5,max = 200, message = "Description must be between 5 and 200 characters")
    private String description;

    @NotBlank(message = "content field must has a value")
    private String content;

    @Positive(message = "Region id field must has a value")
    private Integer regionId;

    @Positive(message = "category id field must has a value")
    private Integer categoryId;

    private List<Integer> articleType;
    private String photoId;

}
