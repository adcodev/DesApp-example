package com.appecommerce.ecommerce.core.usecase.dto;

import com.appecommerce.ecommerce.core.entity.User;

public class ResponseMapper {
    public static User toDomain(User response){
        User user = new User();
        user.setId(response.getId());
        user.setName(response.getName());
        user.setEmail(response.getEmail());
        user.setRole(response.getRole());
        return user;
    }
}
