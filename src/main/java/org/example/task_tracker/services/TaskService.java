package org.example.task_tracker.services;

import org.example.task_tracker.dtos.request.TaskRequest;
import org.example.task_tracker.dtos.response.TaskResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAll(int page, int size, Sort sort);
    TaskResponse getById(int id);
    TaskResponse save(TaskRequest taskRequest);
    TaskResponse update(int id ,TaskRequest taskRequest);
    void deleteById(int id);
}
