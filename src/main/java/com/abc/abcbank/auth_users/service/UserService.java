package com.abc.abcbank.auth_users.service;

import com.abc.abcbank.auth_users.dto.UserDTO;
import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.response.Response;
import org.springframework.data.domain.Page;

public interface UserService {

    User getCurrentLoggedInUser();
    Response<UserDTO> getMyProfile();
    Response<Page<UserDTO>> getAllUsers(int page, int size);
}
