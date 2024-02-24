package org.example.kunuz.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.kunuz.enums.TaskStatus;

@Setter
@Getter
public class TaskDTO {
    private String id;
    private String title;
    private String content;
    private TaskStatus status;

}
