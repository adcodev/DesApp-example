package com.appecommerce.ecommerce.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="users")
public class UserJpaEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String role;

    public UserJpaEntity(){}

    public static class UserJpaRepository {
    }
}
