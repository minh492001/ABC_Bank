package com.abc.abcbank.account.service.impl;

import com.abc.abcbank.account.entity.Account;
import com.abc.abcbank.account.repo.AccountRepository;
import com.abc.abcbank.account.service.AccountService;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.enums.AccountStatus;
import com.abc.abcbank.enums.AccountType;
import com.abc.abcbank.enums.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

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
