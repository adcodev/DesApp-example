package com.appecommerce.ecommerce.core.usecase.service;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.dto.ResponseMapper;
import com.appecommerce.ecommerce.core.usecase.port.in.GetAllUserCase;
import com.appecommerce.ecommerce.core.usecase.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUserService implements GetAllUserCase {
    private final UserRepositoryPort userRepositoryPort;

    public GetAllUserService(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<User> findAll(){
        return userRepositoryPort.findAll().stream().map(ResponseMapper::toDomain).toList();
        //Paginación
        //lógica del filtor por página
    }
}
