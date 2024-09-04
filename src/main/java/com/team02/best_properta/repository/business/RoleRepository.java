package com.team02.best_properta.repository.business;

import com.team02.best_properta.entity.concretes.business.Role;
import com.team02.best_properta.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    @Query("SELECT r FROM Role r WHERE r.roleType = ?1")
    Optional<Role> findByEnumRoleEquals(RoleType roleType);

}
