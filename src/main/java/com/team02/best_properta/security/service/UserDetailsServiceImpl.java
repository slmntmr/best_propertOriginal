package com.team02.best_properta.security.service;

import com.team02.best_properta.entity.concretes.user.Users;
import com.team02.best_properta.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' not found"));

        return UserDetailsImpl.build(user);
    }
}
