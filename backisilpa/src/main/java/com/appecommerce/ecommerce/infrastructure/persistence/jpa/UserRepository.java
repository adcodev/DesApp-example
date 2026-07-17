package com.appecommerce.ecommerce.infrastructure.persistence.jpa;

import com.appecommerce.ecommerce.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserJpaEntity, UUID> {

    @Query(value = "SELECT split_part(email, '@', 2) AS domain, COUNT(*) AS count " +
            "FROM users GROUP BY split_part(email, '@', 2) ORDER BY count DESC", nativeQuery = true)
    List<Object[]> countByEmailDomain();

    @Query(value = "SELECT u.role, COUNT(u) FROM UserJpaEntity u GROUP BY u.role ORDER BY COUNT(u) DESC")
    List<Object[]> countByRole();

    @Query("SELECT u FROM UserJpaEntity u WHERE " +
        "(:emailPattern IS NULL OR LOWER(u.email) LIKE :emailPattern) AND " +
        "(:role IS NULL OR u.role = :role)")
    Page<UserJpaEntity> findByFilters(
            @Param("emailPattern") String emailPattern,
            @Param("role") String role,
            Pageable pageable
    );

}
