package com.appecommerce.ecommerce.core.usecase.service;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.dto.response.*;
import com.appecommerce.ecommerce.core.usecase.port.in.UserReportCase;
import com.appecommerce.ecommerce.core.usecase.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReportService implements UserReportCase {
    private  final UserRepositoryPort userRepositoryPort;

    public UserReportService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserReportResponse getReport(String emailType, String role, int page, int size){
        String domain = resolveEmailDomain(emailType);

        List<CountByDomainResponse> countDomain = userRepositoryPort.countByDomain().stream()
                .map(entry-> new CountByDomainResponse(entry.domain(), entry.count()))
                .toList();

        List<CountByRoleResponse> countByRole = userRepositoryPort.countByRole().stream()
                .map(entry -> new CountByRoleResponse(entry.count(), entry.role()))
                .toList();
        var userPage = userRepositoryPort.findByFilters(domain, role, page, size);
        List<UserResponse> users = userPage.content().stream()
                .map(this::toUserResponse)
                .toList();
        PageResponse<UserResponse> pageResponse = new PageResponse<>(
                users,
                userPage.page(),
                userPage.size(),
                userPage.totalElements(),
                userPage.totalPages()
        );

        return new UserReportResponse(countDomain, countByRole, pageResponse);
    }

    private UserResponse toUserResponse(User user){
        return new UserResponse(
                user.getId().toString(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    private String resolveEmailDomain(String emailType){
        if(emailType == null || emailType.isBlank()){
            return  null;
        }
        return switch (emailType.toLowerCase()){
            case "gmail" -> "gmail.com";
            case "outlook" -> "outlook.com";
            case "hotmail" -> "hotmail.com";
            case "yahoo" -> "yahoo.com";

            default -> emailType.startsWith("@") ? emailType.substring(1) : emailType;
        };
    }


}

