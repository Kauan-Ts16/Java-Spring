package com.api.ParkingControlAPI.repository;

import com.api.ParkingControlAPI.enums.RoleName;
import com.api.ParkingControlAPI.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    RoleModel findByRoleName(RoleName roleName);
}
