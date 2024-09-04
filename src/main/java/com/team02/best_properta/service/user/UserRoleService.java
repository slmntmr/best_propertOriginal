package com.team02.best_properta.service.user;

import com.team02.best_properta.entity.concretes.user.UserRole;
import com.team02.best_properta.entity.enums.RoleType;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class UserRoleService {


    private final UserRoleRepository userRoleRepository;



}
