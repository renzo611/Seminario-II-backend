package com.seminario.backend.controller;

import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.dto.TaskRequestDto;
import com.seminario.backend.dto.TaskResponseDto;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.services.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final ITaskService iTaskService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponseDto> createNewTask(@RequestBody TaskRequestDto taskRequestDto) throws GeneralException {
        return new ResponseEntity<>(this.iTaskService.create(taskRequestDto), CREATED);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<TaskResponseDto>> getTaskByUser(@PathVariable Integer userId) throws GeneralException {
        return new ResponseEntity<>(this.iTaskService.findAllTaskByUser(userId), OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<GeneralResponseDto> updateTask(@PathVariable() Integer taskId ,@RequestBody TaskRequestDto taskRequestDto) throws GeneralException {
        return new ResponseEntity<>(this.iTaskService.update(taskId, taskRequestDto), OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<GeneralResponseDto> deleteTask(@PathVariable() Integer taskId) throws GeneralException {
        return new ResponseEntity<>(this.iTaskService.delete(taskId), OK);
    }
}
