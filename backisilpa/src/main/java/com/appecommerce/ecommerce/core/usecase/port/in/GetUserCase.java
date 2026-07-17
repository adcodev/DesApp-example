package com.appecommerce.ecommerce.core.usecase.port.in;

import com.appecommerce.ecommerce.core.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface GetUserCase {
    Optional<User> getUser(UUID id);
}
