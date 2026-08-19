package com.abc.abcbank.account.service;

import com.abc.abcbank.account.entity.Account;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.enums.AccountType;

public interface AccountService {

    Account createAccount(AccountType accountType, User user);
}
