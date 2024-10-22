package com.api.TaskSystemAPI.components;

import com.api.TaskSystemAPI.enums.RoleName;
import com.api.TaskSystemAPI.model.RoleModel;
import com.api.TaskSystemAPI.model.UserModel;
import com.api.TaskSystemAPI.repository.RoleRepository;
import com.api.TaskSystemAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
            UserModel userDefault = new UserModel();
            userDefault.setName("admin");
            userDefault.setEmail("admin@gmail.com");
            userDefault.setPassword(passwordEncoder.encode("admin-password"));
            RoleModel admin = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
            userDefault.setRoles(Set.of(admin));

            userRepository.save(userDefault);
        }

    }
}
