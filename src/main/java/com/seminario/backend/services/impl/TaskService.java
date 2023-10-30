package com.seminario.backend.services.impl;

import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.dto.TaskRequestDto;
import com.seminario.backend.dto.TaskResponseDto;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.models.Contact;
import com.seminario.backend.models.Task;
import com.seminario.backend.models.Users;
import com.seminario.backend.repository.ContactRepository;
import com.seminario.backend.repository.TaskRepository;
import com.seminario.backend.repository.UserRepository;
import com.seminario.backend.services.ITaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Override
    public GeneralResponseDto create(TaskRequestDto taskRequestDto) throws GeneralException {
        Users user = this.userRepository.findById(taskRequestDto.getUserId()).orElseThrow(() -> new GeneralException("The user don't exist",403));
        Contact contact = this.contactRepository.findById(taskRequestDto.getContactId()).orElseThrow(() -> new GeneralException("The contact don't eixst",403));

        Task task = this.taskRepository.save(new Task(
                taskRequestDto.getName(),
                taskRequestDto.getDescription(),
                taskRequestDto.getStartDate(),
                taskRequestDto.getEndDate(),
                user,
                contact
        ));

        if(task == null || task.getId() <= 0){
            throw new GeneralException("Error saving new task", 500);
        }


        return new GeneralResponseDto(201, "Task created");
    }

    @Override
    public List<TaskResponseDto> findAllTaskByUser(Integer userId) throws GeneralException {
        Users user = this.userRepository.findById(userId).orElseThrow(() -> new GeneralException("The user don't exist",403));

        Set<Task> tasks = user.getTaks();

        return tasks.stream().map(task -> new TaskResponseDto(
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getStartDate(),
                    task.getEndDate(),
                    task.getContact().getName(),
                    task.getContact().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public GeneralResponseDto update(Integer taskId,TaskRequestDto taskRequestDto) throws GeneralException {
        Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new GeneralException("The task doesn't exist", 403));

        if(taskRequestDto.getContactId() != null && taskRequestDto.getContactId() != task.getContact().getId()){
            Contact contact = this.contactRepository.findById(taskRequestDto.getContactId()).orElseThrow(() -> new GeneralException("The contact doesn't exist", 403));
            task.setContact(contact);
        }

        task.setEndDate(taskRequestDto.getEndDate());
        task.setName(taskRequestDto.getName());
        task.setDescription(taskRequestDto.getDescription());

        this.taskRepository.save(task);
        return new GeneralResponseDto(200, "Task updated");
    }

    @Override
    public GeneralResponseDto delete(Integer taskId) throws GeneralException {
        Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new GeneralException("The task doesn't exist", 403));

        task.setDeletedAt(true);

        this.taskRepository.save(task);

        return new GeneralResponseDto(200, "Task deleted");
    }


}
