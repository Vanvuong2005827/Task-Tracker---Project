package org.example.task_tracker.dtos.mapper;

import org.example.task_tracker.dtos.request.TaskRequest;
import org.example.task_tracker.dtos.response.TaskResponse;
import org.example.task_tracker.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task RequestToTask(TaskRequest taskRequest);
    TaskResponse TaskToResponse(Task task);
    List<TaskResponse> TasksToResponse(List<Task> tasks);

    void updateTaskFromRequest(TaskRequest req, @MappingTarget Task task);
}
