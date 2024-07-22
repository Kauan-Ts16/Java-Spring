package com.api.ParkingControlAPI.service;

import com.api.ParkingControlAPI.components.validate.UserValidate;
import com.api.ParkingControlAPI.dto.post.UserPost;
import com.api.ParkingControlAPI.dto.put.UserPut;
import com.api.ParkingControlAPI.enums.RoleName;
import com.api.ParkingControlAPI.mapper.UserMapper;
import com.api.ParkingControlAPI.model.RoleModel;
import com.api.ParkingControlAPI.model.UserModel;
import com.api.ParkingControlAPI.repository.RoleRepository;
import com.api.ParkingControlAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        userValidate.verifyExists(id);
        userRepository.deleteById(id);
    }

    public UserModel update(UserPut userPut) {
        userValidate.updateValid(userPut);
        var userModel = UserMapper.toMapper(userPut, findById(userPut.id()));
        encodePassword(userModel);

        return userRepository.save(userModel);
    }

    public UserModel updateAdmin(UUID id) {
        var userModel = findById(id);
        adminRole(userModel);

        return userRepository.save(userModel);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel findById(UUID id) {
        return userValidate.findById(id);
    }

    public void encodePassword(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
    }

    public void userRole(UserModel userModel) {
        RoleModel role = roleRepository.findByRoleName(RoleName.ROLE_USER);
        userModel.setRoles(Collections.singletonList(role));
    }

    public void adminRole(UserModel userModel) {
        RoleModel role = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
        userModel.getRoles().add(role);
    }

}
