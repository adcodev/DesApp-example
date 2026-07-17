package com.appecommerce.ecommerce.core.usecase.service;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.dto.RequestMapper;
import com.appecommerce.ecommerce.core.usecase.port.in.RegisterUserCase;
import com.appecommerce.ecommerce.core.usecase.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserCase {
    private final UserRepositoryPort userRepositoryPort;

    public RegisterUserService(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User register(User request){
       return userRepositoryPort.save(RequestMapper.fromDomain(request));
    }
}
