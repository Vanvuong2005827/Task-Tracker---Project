package org.example.task_tracker.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.task_tracker.dtos.mapper.TaskMapper;
import org.example.task_tracker.dtos.request.TaskRequest;
import org.example.task_tracker.dtos.response.TaskResponse;
import org.example.task_tracker.exceptions.extendedExceptions.ResourceNotFoundException;
import org.example.task_tracker.models.Task;
import org.example.task_tracker.repository.TaskRepository;
import org.example.task_tracker.services.TaskService;
import org.example.task_tracker.utils.PostPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;


    @Override
    public List<TaskResponse> getAll(int page, int size, Sort sort) {
        List<Task> tasks = taskRepository.findAll(PostPageRequest.of(page, size, sort)).getContent();
        if (tasks.isEmpty()) {
            throw new ResourceNotFoundException("No tasks found");
        }
        return taskMapper.TasksToResponse(tasks);
    }

    @Override
    public TaskResponse getById(int id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return taskMapper.TaskToResponse(task);
    }

    @Override
    public TaskResponse save(TaskRequest taskRequest) {
        Task task = taskMapper.RequestToTask(taskRequest);
        taskRepository.save(task);
        return taskMapper.TaskToResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse update(int id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setId(id);
        taskMapper.updateTaskFromRequest(taskRequest, task);
            taskRepository.save(task);
        return taskMapper.TaskToResponse(task);
    }

    @Override
    public void deleteById(int id) {

    }
}
