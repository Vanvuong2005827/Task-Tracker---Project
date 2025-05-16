package org.example.task_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.task_tracker.dtos.request.TaskRequest;
import org.example.task_tracker.dtos.response.TaskResponse;
import org.example.task_tracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/task")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskController {
    @Autowired
    TaskService taskService;

    //--------------------------------getAllTask-----------------------------------------
    @Operation(summary = "get all task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Some thing error")
    })
    @GetMapping("/")
    public ResponseEntity<List<TaskResponse>> getTasks(Pageable pageable) {
        return new ResponseEntity<>(taskService.getAll(pageable),HttpStatus.OK);
    }

    //--------------------------------getTaskById-----------------------------------------
    @Operation(summary = "get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") int id) {
        return new ResponseEntity<>(taskService.getById(id),HttpStatus.OK);
    }

    //--------------------------------createTask-----------------------------------------
    @Operation(summary = "create task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")
    })
    @PostMapping("/")
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest taskRequest) {
        return new ResponseEntity<>(taskService.save(taskRequest),HttpStatus.CREATED);
    }

    //--------------------------------updateTask-----------------------------------------
    @Operation(summary = "update task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") int id ,@Valid @RequestBody TaskRequest taskRequest) {
        return new ResponseEntity<>(taskService.update(id, taskRequest),HttpStatus.OK);
    }
}
