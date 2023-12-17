package com.ada.bookStore.controller;

import com.ada.bookStore.controller.dto.UserRequest;
import com.ada.bookStore.controller.dto.UserResponse;
import com.ada.bookStore.controller.exception.IdNotFoundError;
import com.ada.bookStore.controller.exception.PasswordValidationError;
import com.ada.bookStore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

//    @RequestMapping
//    public ResponseEntity<Page<UserResponse>>getUsers(
//            @RequestParam(
//                    value = "page",
//                    required = false,
//                    defaultValue = "0"
//            ) int page,
//            @RequestParam(
//                    value = "size",
//                    required = false,
//                    defaultValue = "10"
//            ) int size,
//            @RequestParam(
//                    value = "direction",
//                    required = false,
//                    defaultValue = "ASC"
//            ) String direction
//
//    ){
//
//        return ResponseEntity.ok(userService.getUsers(page, size, direction));
//    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(
            @Valid @RequestBody UserRequest userDTO
    ) throws PasswordValidationError {
        UserResponse user =  userService.saveUser(userDTO);
        return ResponseEntity.created(URI.create("/user/"+user.getId())).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer id) throws IdNotFoundError {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserResponse>> getAllUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getAllByName(name));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) throws IdNotFoundError {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserRequest userRequest
    ){
        return  ResponseEntity.ok(userService.updateUser(id, userRequest));
    }
}

