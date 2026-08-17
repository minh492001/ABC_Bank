package com.abc.abcbank.auth_users.service;

import com.abc.abcbank.auth_users.dto.UserDTO;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.response.Response;

public interface UserService {

    User getCurrentLoggedInUser();
    Response<UserDTO> getMyProfile();
}
