package com.appecommerce.ecommerce.core.usecase.port.in;

import com.appecommerce.ecommerce.core.entity.User;

import java.util.List;

public interface GetAllUserCase {
    List<User> findAll();
}
