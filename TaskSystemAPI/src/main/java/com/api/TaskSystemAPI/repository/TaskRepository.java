package com.api.TaskSystemAPI.repository;

import com.api.TaskSystemAPI.model.TaskModel;
import com.api.TaskSystemAPI.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    
    List<TaskModel> findAllByUserModel(UserModel userModel);

    boolean existsByUserModelUserId(UUID id);
}
