package com.api.ParkingControlAPI.components;

import com.api.ParkingControlAPI.enums.RoleName;
import com.api.ParkingControlAPI.model.RoleModel;
import com.api.ParkingControlAPI.model.UserModel;
import com.api.ParkingControlAPI.repository.RoleRepository;
import com.api.ParkingControlAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            RoleModel adminRole = new RoleModel(RoleName.ROLE_ADMIN);
            RoleModel userRole = new RoleModel(RoleName.ROLE_USER);

            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }

        if (userRepository.count() == 0) {
            UserModel defaultUser = new UserModel();
            defaultUser.setUsername("admin");
            defaultUser.setPassword(passwordEncoder.encode("password-admin"));
            RoleModel admin = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
            defaultUser.setRoles(Collections.singletonList(admin));

            userRepository.save(defaultUser);
        }

    }
}
