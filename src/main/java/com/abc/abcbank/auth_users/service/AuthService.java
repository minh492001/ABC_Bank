package com.abc.abcbank.auth_users.service;

import com.abc.abcbank.auth_users.dto.RegistrationRequest;
import com.abc.abcbank.response.Response;

public interface AuthService {

    Response<String> register (RegistrationRequest request);

}
