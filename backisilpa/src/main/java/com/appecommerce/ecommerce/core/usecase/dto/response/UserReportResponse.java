package com.appecommerce.ecommerce.core.usecase.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserReportResponse {
    private List<CountByDomainResponse> countByDomain;
    private List<CountByRoleResponse> countByRole;
    private PageResponse<UserResponse> users;

    public UserReportResponse(List<CountByDomainResponse> countByDomain, List<CountByRoleResponse> countByRole, PageResponse<UserResponse> users) {
        this.countByDomain = countByDomain;
        this.countByRole = countByRole;
        this.users = users;
    }
}
