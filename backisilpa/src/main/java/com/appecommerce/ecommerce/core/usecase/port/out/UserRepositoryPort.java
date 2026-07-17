package com.appecommerce.ecommerce.core.usecase.port.out;
import com.appecommerce.ecommerce.core.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();

    List<DomainCount> countByDomain();
    List<RoleCount> countByRole();
    UserPage findByFilters(String domain, String role, int page, int size);

    record DomainCount(String domain, long count){}
    record RoleCount(String role, long count){}
    record UserPage(List<User> content, int page, int size, long totalElements, int totalPages){}
}
