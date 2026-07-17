package com.appecommerce.ecommerce.infrastructure.persistence.adapter;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.port.out.UserRepositoryPort;
import com.appecommerce.ecommerce.infrastructure.persistence.entity.UserJpaEntity;
import com.appecommerce.ecommerce.infrastructure.persistence.jpa.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user){
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = userRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id){
        return userRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<DomainCount> countByDomain(){
        return userRepository.countByEmailDomain().stream()
                .map(row -> new DomainCount((String)  row[0], ((Number) row[1]).longValue()))
                .toList();
    }

    @Override
    public List<RoleCount> countByRole(){
        return userRepository.countByRole().stream()
                .map(row-> new RoleCount((String)  row[0], ((Number) row[1]).longValue()))
                .toList();
    }

    @Override
    public UserPage findByFilters(String domain, String role, int page, int size){
         // --- CORREGIDO: Se agregó '%' antes del '@' para que LIKE funcione correctamente ---
         String emailPattern = domain != null ? "%@" + domain.toLowerCase() : null;

        Page<UserJpaEntity> result = userRepository.findByFilters(
          emailPattern,
          role, PageRequest.of(page, size)
        );

        List<User> users = result.getContent().stream().map(this::toDomain).toList();

        return new UserPage(
                users,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }



    private UserJpaEntity toEntity(User user){
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        return entity;
    }

    private User toDomain(UserJpaEntity entity){
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole());
        return user;
    }
}
