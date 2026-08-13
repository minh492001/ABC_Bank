package com.abc.abcbank.auth_users.service;

import com.abc.abcbank.auth_users.dto.LoginRequest;
import com.abc.abcbank.auth_users.dto.LoginResponse;
import com.abc.abcbank.auth_users.dto.RegistrationRequest;
import com.abc.abcbank.auth_users.dto.ResetPasswordRequest;
import com.abc.abcbank.response.Response;

public interface AuthService {

    Response<String> register (RegistrationRequest request);
    Response<LoginResponse> login(LoginRequest loginRequest);
    Response<? > forgetPassword(String email);
    Response<? > updatePasswordViaResetCode(ResetPasswordRequest resetPasswordRequest);

}
