package com.ada.bookStore.service;

import com.ada.bookStore.controller.dto.UserRequest;
import com.ada.bookStore.controller.dto.UserResponse;
import com.ada.bookStore.controller.exception.IdNotFoundError;
import com.ada.bookStore.controller.exception.PasswordValidationError;
import com.ada.bookStore.model.User;
import com.ada.bookStore.repository.UserRepository;
import com.ada.bookStore.utils.UserConvert;
import com.ada.bookStore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public class UserService {


    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void validUser(Integer id) throws IdNotFoundError {
        Optional<User> found = userRepository.findById(id);
        if (found.isEmpty()) {
            throw new IdNotFoundError("Cliente não encontrado");
        }
    }

    public UserResponse saveUser(UserRequest userDTO) throws PasswordValidationError {
        User user = UserConvert.toEntity(userDTO);

        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        user.setActive(true);
        if(!Validator.passwordValidate(user.getPassword())) throw new PasswordValidationError("Senha deve seguir o padrao");
        User userEntity = userRepository.save(user);
        return UserConvert.toResponse(userEntity);
    }

    public UserResponse getUserById(Integer id) throws IdNotFoundError {
        validUser(id);
        Optional<User> userResponse =  userRepository.findById(id);
        return UserConvert.toResponse(userResponse.get());
    }

    public List<UserResponse> getAllByName(String name){
        return UserConvert.toResponseList(userRepository.findAllByName(name));
    }

//    public Page<UserResponse> getUsers(int page, int size, String direction){
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "name");
//        Page<User> users = userRepository.findAll(pageRequest);
//        return UserConvert.toResponsePage(users);
//
//    }

    public void deleteUser(Integer id) throws IdNotFoundError {
        validUser(id);
        User user = userRepository.findById(id).get();
        user.setActive(false);
        userRepository.save(user);
    }

    public UserResponse updateUser(Integer id, UserRequest userRequest){
        User user = UserConvert.toEntity(userRequest);
        user.setId(id);
        return UserConvert.toResponse(userRepository.save(user));
    }

}
