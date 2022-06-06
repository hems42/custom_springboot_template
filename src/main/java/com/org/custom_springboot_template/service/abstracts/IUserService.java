package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.core.utilities.results.DataResult;
import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.product.dto.UserDto;
import com.org.custom_springboot_template.product.request.createRequest.UserRequestCreate;
import com.org.custom_springboot_template.product.request.updateRequest.UserRequestUpdate;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IUserService {

    UserDto addUser(UserRequestCreate requestCreate);

    UserDto getUserById(String id);

    UserDto getUserByEmail(String email);

    UserDto getUserByNickName(String userNickName);

    List<UserDto> getAllUsers();

    UserDto updateUserById(String id, UserRequestUpdate requestUpdate);

    Boolean deleteUserById(String id);

    //LOG
    DataResult addLogInLogToUser(@Nullable String userNickName, @Nullable User user);

    DataResult addLogOutLogToUser(@Nullable String userNickName, @Nullable User user);

    DataResult addSignUpLogToUser(@Nullable String userNickName, @Nullable User user);

    DataResult addRegisteredLogToUser(@Nullable String userNickName, @Nullable User user);


    Boolean setConfirmedUser(User user);

    Boolean verifyUserPassword(User user, String password);
}
