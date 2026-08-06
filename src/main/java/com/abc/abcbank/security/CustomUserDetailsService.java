package com.abc.abcbank.security;

import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.auth_users.repo.UserRepository;
import com.abc.abcbank.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("Email Not Found"));
        return AuthUser.builder().user(user).build();
    }
}
