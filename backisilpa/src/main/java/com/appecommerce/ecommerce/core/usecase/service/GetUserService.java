package com.appecommerce.ecommerce.core.usecase.service;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.dto.ResponseMapper;
import com.appecommerce.ecommerce.core.usecase.port.in.GetUserCase;
import com.appecommerce.ecommerce.core.usecase.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUserService implements GetUserCase {
    private final UserRepositoryPort userRepositoryPort;

    public GetUserService(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> getUser(UUID id){
        return userRepositoryPort.findById(id).map(ResponseMapper::toDomain);
    }
}
