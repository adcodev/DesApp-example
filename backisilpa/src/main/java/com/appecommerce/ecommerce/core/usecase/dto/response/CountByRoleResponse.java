package com.appecommerce.ecommerce.core.usecase.dto.response;

import lombok.Data;

@Data
public class CountByRoleResponse {
    private String role;
    private long count;

    public CountByRoleResponse(long count, String role) {
        this.count = count;
        this.role = role;
    }
}
