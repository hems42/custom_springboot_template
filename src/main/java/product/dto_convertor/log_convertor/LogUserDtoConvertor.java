package product.dto_convertor.log_convertor;

import entity.log.LogUser;
import org.springframework.stereotype.Component;
import product.dto.LogUserDto;

@Component
public class LogUserDtoConvertor {

    public LogUser convert(LogUserDto from) {
        return new LogUser(
                from.getId(),
                from.getUserOperationType(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }

    public LogUserDto convert(LogUser from) {
        return new LogUserDto(
                from.getId(),
                from.getTransactionType(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }
}
