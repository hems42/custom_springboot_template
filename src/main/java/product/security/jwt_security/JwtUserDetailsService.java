package product.security.jwt_security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import product.dto.UserDto;
import product.dto_convertor.primary_convertor.UserDtoConvertor;


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

        throw new NotFoundException(CoreEnumExceptionMessages.NOT_FOUND_USER,"not found user by token username");
    }

}