package com.seminario.backend.services;

import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.dto.TaskRequestDto;
import com.seminario.backend.dto.TaskResponseDto;
import com.seminario.backend.exception.GeneralException;

import java.util.List;

public interface ITaskService {
    GeneralResponseDto create(TaskRequestDto taskRequestDto) throws GeneralException;
    List<TaskResponseDto> findAllTaskByUser(Integer userId) throws GeneralException;
    GeneralResponseDto update(Integer taskId, TaskRequestDto taskRequestDto) throws GeneralException;
    GeneralResponseDto delete(Integer taskId) throws GeneralException;
}
