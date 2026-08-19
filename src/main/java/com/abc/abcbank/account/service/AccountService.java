package com.abc.abcbank.account.service;

import com.abc.abcbank.account.dto.AccountDTO;
import com.abc.abcbank.account.entity.Account;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.enums.AccountType;
import com.abc.abcbank.response.Response;

import java.util.List;

public interface AccountService {

    Account createAccount(AccountType accountType, User user);
    Response<List<AccountDTO>> getMyAccounts();
}
