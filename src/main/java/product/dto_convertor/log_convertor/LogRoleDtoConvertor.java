package product.dto_convertor.log_convertor;

import entity.log.LogRole;
import org.springframework.stereotype.Component;
import product.dto.LogRoleDto;

@Component
public class LogRoleDtoConvertor {

    LogRole convert(LogRoleDto from) {
        return new LogRole(
                from.getId(),
                from.getCrud(),
                from.getRole(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }

    LogRoleDto convert(LogRole from) {
        return new LogRoleDto(
                from.getId(),
                from.getTransactionType(),
                from.getRole(),
                from.getUser(),
                from.getCreatedDate() == null ? null : from.getCreatedDate()
        );
    }
}
