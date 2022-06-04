package product.dto_convertor.primary_convertor;

import entity.primary.User;
import org.springframework.stereotype.Component;
import product.dto.UserDto;

import java.util.HashSet;

@Component
public class UserDtoConvertor {

    public User convert(UserDto from) {
        return new User(
                from.getId(),
                from.getUsername(),
                from.getEmail(),
                from.getPassword(),
                from.getActive(),
                from.getRegistered(),
                from.getRoles() == null ? new HashSet<>() : from.getRoles(),
                from.getCreatedDate() == null ? null : from.getCreatedDate(),
                from.getUpdatedDate() == null ? null : from.getUpdatedDate()
        );
    }

    public UserDto convert(User from) {
        return new UserDto(
                from.getId(),
                from.getUsername(),
                from.getEmail(),
                from.getPassword(),
                from.getIsActive(),
                from.getIsRegistered(),
                from.getRoles() == null ? new HashSet<>() : from.getRoles(),
                from.getCreatedDate() == null ? null : from.getCreatedDate(),
                from.getUpdatedDate() == null ? null : from.getUpdatedDate()

        );
    }
}
