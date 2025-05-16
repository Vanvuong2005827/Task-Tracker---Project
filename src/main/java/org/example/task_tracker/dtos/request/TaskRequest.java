package org.example.task_tracker.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.task_tracker.enums.Status;

import java.time.LocalDate;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class TaskRequest {
    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 20, message = "Mô tả không được vượt quá 20 ký tự")
    String description;
    Status status;
}
