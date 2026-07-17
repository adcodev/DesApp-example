package com.appecommerce.ecommerce.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID id;
    private String name;
    private String email;
    private String role;

    public User() {}
}
