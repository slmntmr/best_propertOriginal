package com.team02.best_properta.repository.user;

import com.team02.best_properta.entity.concretes.user.UserRole;
import com.team02.best_properta.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {


}




