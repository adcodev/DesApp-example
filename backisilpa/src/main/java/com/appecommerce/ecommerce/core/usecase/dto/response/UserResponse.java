package com.appecommerce.ecommerce.core.usecase.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String role;

    public UserResponse(String id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
