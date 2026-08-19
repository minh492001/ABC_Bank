package com.abc.abcbank.account.service.impl;

import com.abc.abcbank.account.dto.AccountDTO;
import com.abc.abcbank.account.entity.Account;
import com.abc.abcbank.account.repo.AccountRepository;
import com.abc.abcbank.account.service.AccountService;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.auth_users.service.UserService;
import com.abc.abcbank.enums.AccountStatus;
import com.abc.abcbank.enums.AccountType;
import com.abc.abcbank.enums.Currency;
import com.abc.abcbank.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    private final Random random = new Random();

    @Override
    public Account createAccount(AccountType accountType, User user) {

        log.info("Inside createAccount() ");

        String accountNumber = generateAccountNumber();
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountType(accountType)
                .currency(Currency.VND)
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        return accountRepository.save(account);

    }

    @Override
    public Response<List<AccountDTO>> getMyAccounts() {
        User user = userService.getCurrentLoggedInUser();
        List<AccountDTO> accounts = accountRepository.findByUserId(user.getId())
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .toList();

        return Response.<List<AccountDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("User accounts fetched successfully")
                .data(accounts)
                .build();
    }

    private String generateAccountNumber() {
        String accountNumber;

        do {
            // Generate a random 8-digit number (from 10.000.000 to 99.999.999)
            // and plus it with the prefix "66"
            accountNumber = "66" + (random.nextInt(90000000) + 10000000);
        }
        while (accountRepository.findByAccountNumber(accountNumber).isPresent());

        log.info("account number generate {}", accountRepository);

        return accountNumber;
    }
}
