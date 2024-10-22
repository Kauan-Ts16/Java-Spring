package com.api.TaskSystemAPI.repository;

import com.api.TaskSystemAPI.enums.RoleName;
import com.api.TaskSystemAPI.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    RoleModel findByRoleName(RoleName roleName);
}
