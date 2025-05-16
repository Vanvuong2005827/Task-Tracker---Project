package org.example.task_tracker.dtos.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task_tracker.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    int id;
    String description;
    Status status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
