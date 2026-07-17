package com.appecommerce.ecommerce.core.usecase.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String email;
    private String role;

    public UserRequest(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
