package com.appecommerce.ecommerce.core.usecase.port.in;

import java.util.UUID;

public interface DeleteUserCase {
    void delete(UUID id);
}
