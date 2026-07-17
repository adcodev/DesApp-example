package com.appecommerce.ecommerce.core.usecase.port.in;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.dto.request.UserRequest;

import java.util.UUID;

public interface UpdateUseCase {
    User update(UUID id, UserRequest reuquest);
}
