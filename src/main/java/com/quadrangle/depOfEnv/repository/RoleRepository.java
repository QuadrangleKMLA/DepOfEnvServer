package com.quadrangle.depOfEnv.repository;

import com.quadrangle.depOfEnv.entity.auth.ERole;
import com.quadrangle.depOfEnv.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
    Boolean existsByName(ERole name);
}
