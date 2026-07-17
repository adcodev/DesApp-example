package com.appecommerce.ecommerce.core.usecase.dto;

import com.appecommerce.ecommerce.core.entity.User;

public class RequestMapper {
    public static User fromDomain(User request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return user;
    }
}
