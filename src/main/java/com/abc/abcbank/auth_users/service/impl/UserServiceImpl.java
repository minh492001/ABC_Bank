package com.abc.abcbank.auth_users.service.impl;

import com.abc.abcbank.auth_users.dto.UpdatePasswordRequest;
import com.abc.abcbank.auth_users.dto.UserDTO;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.auth_users.repo.UserRepository;
import com.abc.abcbank.auth_users.service.UserService;
import com.abc.abcbank.exceptions.BadRequestException;
import com.abc.abcbank.exceptions.NotFoundException;
import com.abc.abcbank.notification.dto.NotificationDTO;
import com.abc.abcbank.notification.service.NotificationService;
import com.abc.abcbank.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotFoundException("User is not authenticated");
        }
        String email = authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
