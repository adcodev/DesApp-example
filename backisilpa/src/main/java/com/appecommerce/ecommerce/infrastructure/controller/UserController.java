package com.appecommerce.ecommerce.infrastructure.controller;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.dto.RequestMapper;
import com.appecommerce.ecommerce.core.usecase.dto.ResponseMapper;

import com.appecommerce.ecommerce.core.usecase.port.in.GetAllUserCase;
import com.appecommerce.ecommerce.core.usecase.port.in.GetUserCase;
import com.appecommerce.ecommerce.core.usecase.port.in.RegisterUserCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private  final RegisterUserCase registerUserCase;
    private final GetUserCase getUserCase;
    private final GetAllUserCase getAllUserCase;

    public UserController(RegisterUserCase registerUserCase, GetUserCase getUserCase, GetAllUserCase getAllUserCase){
        this.registerUserCase = registerUserCase;
        this.getUserCase = getUserCase;
        this.getAllUserCase = getAllUserCase;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerCtrl(@RequestBody User request) {
        User response = registerUserCase.register(RequestMapper.fromDomain(request));
        return ResponseEntity.ok(ResponseMapper.toDomain(response));
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = getAllUserCase.findAll().stream().map(ResponseMapper::toDomain).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        User  user = getUserCase.getUser(id).orElseThrow(()->new RuntimeException("Not found"));
        return ResponseEntity.ok(ResponseMapper.toDomain(user));
    }

}
