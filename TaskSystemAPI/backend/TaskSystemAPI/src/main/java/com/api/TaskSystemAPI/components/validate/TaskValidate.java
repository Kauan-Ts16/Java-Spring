package com.api.TaskSystemAPI.components.validate;

import com.api.TaskSystemAPI.exception.ExceptionGeneric;
import com.api.TaskSystemAPI.model.TaskModel;
import com.api.TaskSystemAPI.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskValidate {

    @Autowired
    private TaskRepository taskRepository;

    public void verifyExists(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new ExceptionGeneric("Task not exists!", "Task with id: "+ id + " not exists.", 404);
        }
    }

    public TaskModel findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new ExceptionGeneric("Task not found!", "Task with id: "+ id +" not found.", 404));
    }
}
