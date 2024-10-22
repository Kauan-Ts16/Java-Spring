package com.api.TaskSystemAPI.mapper;

import com.api.TaskSystemAPI.dto.post.TaskPost;
import com.api.TaskSystemAPI.dto.put.TaskPut;
import com.api.TaskSystemAPI.model.TaskModel;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TaskMapper {

    public static TaskModel toMapper(TaskPost input) {
        TaskModel output = new TaskModel();
        output.setDueDate(Instant.now().plus(7, ChronoUnit.DAYS));
        BeanUtils.copyProperties(input, output);
        return output;
    }

    public static TaskModel toMapper(TaskPut input, TaskModel output) {
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
