package com.abc.abcbank.account.controller;

import com.abc.abcbank.account.service.AccountService;
import com.abc.abcbank.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/me")
    public ResponseEntity<Response<?>> getMyAccounts () {
        return ResponseEntity.ok(accountService.getMyAccounts());
    }
}
