package com.org.custom_springboot_template.product.security.jwt_security;

import com.org.custom_springboot_template.core.enums.CoreEnumExceptionMessages;
import com.org.custom_springboot_template.core.exceptions.exceptionModels.NotFoundException;
import com.org.custom_springboot_template.product.dto.UserDto;
import com.org.custom_springboot_template.product.dto_convertor.primary_convertor.UserDtoConvertor;
import com.org.custom_springboot_template.service.abstracts.IUserService;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final IUserService userService;
    private final UserDtoConvertor userDtoConvertor;

    public JwtUserDetailsService(IUserService userService, UserDtoConvertor userDtoConvertor) {
        this.userService = userService;
        this.userDtoConvertor = userDtoConvertor;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {

        UserDto userDtoFound =userService.getUserByNickName(username);

        UserDto userDto= userDtoFound !=null ?  userDtoFound :null ;

        if (userDto!=null) {

            User user =userDtoConvertor.convert(userDto);

            return new JwtUserDetail(user); }

        throw new NotFoundException(CoreEnumExceptionMessages.D_NOT_FOUND_USER,"not found user by token username");
    }

}