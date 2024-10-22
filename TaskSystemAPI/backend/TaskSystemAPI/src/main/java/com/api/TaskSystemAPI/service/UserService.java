package com.api.TaskSystemAPI.service;

import com.api.TaskSystemAPI.components.validate.UserValidate;
import com.api.TaskSystemAPI.dto.post.UserPost;
import com.api.TaskSystemAPI.dto.put.UserPut;
import com.api.TaskSystemAPI.enums.RoleName;
import com.api.TaskSystemAPI.mapper.UserMapper;
import com.api.TaskSystemAPI.model.RoleModel;
import com.api.TaskSystemAPI.model.UserModel;
import com.api.TaskSystemAPI.repository.RoleRepository;
import com.api.TaskSystemAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidate userValidate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public UserModel save(UserPost userPost) {
        userValidate.isValid(userPost);
        UserModel userModel = UserMapper.toMapper(userPost);
        encodePassword(userModel);
        userRole(userModel);

        return userRepository.save(userModel);
    }

    @Transactional
    public void delete(UUID id) {
        userValidate.validateDelete(id);
        userRepository.deleteById(id);
    }

    public UserModel update(UUID id, UserPut userPut) {
        userValidate.validateUpdate(id, userPut);
        UserModel userModel = UserMapper.toMapper(userPut, findById(id));
        encodePassword(userModel);

        return userRepository.save(userModel);
    }

    public UserModel findById(UUID id) {
        return userValidate.findById(id);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public void encodePassword(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
    }

    public void userRole(UserModel userModel) {
        RoleModel role = roleRepository.findByRoleName(RoleName.ROLE_USER);
        userModel.setRoles(Set.of(role));
    }

    public void adminRole(UserModel userModel) {
        RoleModel role = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
        userModel.setRoles(Set.of(role));
    }

}
