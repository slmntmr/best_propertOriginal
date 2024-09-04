package com.team02.best_properta.service.business;


import com.team02.best_properta.entity.concretes.business.Role;
import com.team02.best_properta.entity.enums.RoleType;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.repository.business.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;



    public Role getUserRole(RoleType roleType) {
        return roleRepository.findByEnumRoleEquals(roleType).orElseThrow(() ->
                new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));

    }


    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

}

