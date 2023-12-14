package com.ada.Bookshop.service;

import com.ada.Bookshop.controller.dto.UserRequest;
import com.ada.Bookshop.controller.dto.UserResponse;
import com.ada.Bookshop.controller.exception.PasswordValidationError;
//import com.ada.AccessoriesMarketplace.model.QUser;
import com.ada.Bookshop.model.User;
import com.ada.Bookshop.repository.UserRepository;
import com.ada.Bookshop.utils.UserConvert;
import com.ada.Bookshop.utils.Validator;
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
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Page<UserResponse> getUsers(int page, int size, String direction){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "name");
        Page<User> users = userRepository.findAll(pageRequest);
        return UserConvert.toResponsePage(users);

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

    public UserResponse getUserById(Integer id){
        Optional<User> userResponse =  userRepository.findById(id);
        if(userResponse.isPresent()){
            return UserConvert.toResponse(userResponse.get());
        } else {
            throw new RuntimeException("nao encontrado");
        }
    }

    public List<UserResponse> getAllByName(String name){
        return UserConvert.toResponseList(userRepository.findAllByName(name));
    }

    public void deleteUser(Integer id){
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
    }

    public UserResponse updateUser(Integer id, UserRequest userRequest){
        User user = UserConvert.toEntity(userRequest);
        user.setId(id);
        return UserConvert.toResponse(userRepository.save(user));
    }

}
