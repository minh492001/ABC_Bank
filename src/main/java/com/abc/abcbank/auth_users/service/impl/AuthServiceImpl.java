package com.abc.abcbank.auth_users.service.impl;

import com.abc.abcbank.account.entity.Account;
import com.abc.abcbank.auth_users.dto.RegistrationRequest;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.auth_users.repo.UserRepository;
import com.abc.abcbank.auth_users.service.AuthService;
import com.abc.abcbank.enums.AccountType;
import com.abc.abcbank.enums.Currency;
import com.abc.abcbank.exceptions.BadRequestException;
import com.abc.abcbank.exceptions.NotFoundException;
import com.abc.abcbank.notification.dto.NotificationDTO;
import com.abc.abcbank.notification.service.NotificationService;
import com.abc.abcbank.response.Response;
import com.abc.abcbank.role.entity.Role;
import com.abc.abcbank.role.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationService notificationService;


    @Value("${password.reset.link}")
    private String resetLink;

    @Override
    public Response<String> register(RegistrationRequest request) {
        List<Role> roles;
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("CUSTOMER").orElseThrow(() -> new NotFoundException("ROLE NOT FOUND"));
            roles = Collections.singletonList(defaultRole);
        } else {
            roles = request.getRoles()
                    .stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new NotFoundException("ROLE NOT FOUND" + roleName)))
                    .toList();
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exist");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .active(true)
                .build();

        User savedUser = userRepository.save(user);

        // TODO: Auto generate an account number for user
//        Account savedAccount = accountService.createAccount(AccountType.SAVINGS, savedUser);

        // Send a welcome email to user's email
        Map<String, Object> vars = new HashMap<>();
        vars.put("name", savedUser.getFirstName());
        NotificationDTO notificationDTO = NotificationDTO.builder()
                .recipient(savedUser.getEmail())
                .subject("Welcome to ABC Bank !")
                .templateName("welcome")
                .templateVariables(vars)
                .build();

        notificationService.sendEmail(notificationDTO, savedUser);

        // Send account details
        Map<String, Object> accountVars = new HashMap<>();
        accountVars.put("name", savedUser.getFirstName());
//        accountVars.put("accountNumber", savedAccount.getAccountNumber());
        accountVars.put("accountType", AccountType.SAVINGS.name());
        accountVars.put("currency", Currency.VND);
        NotificationDTO accountDetails = NotificationDTO.builder()
                .recipient(savedUser.getEmail())
                .subject("Your bank account has been created")
                .templateName("account-created")
                .templateVariables(accountVars)
                .build();

        notificationService.sendEmail(accountDetails, savedUser);

        return Response.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Your account has been created successfully!")
//                .data("Account details has been sent to your email. Your account number is: " + savedAccount.getAccountNumber())
                .build();
    }
}
