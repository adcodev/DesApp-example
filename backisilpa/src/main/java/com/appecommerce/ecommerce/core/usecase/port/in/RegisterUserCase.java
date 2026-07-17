package com.appecommerce.ecommerce.core.usecase.port.in;

import com.appecommerce.ecommerce.core.entity.User;

public interface RegisterUserCase {
    User register(User userRequest);
}
