package com.org.custom_springboot_template.service.abstracts;

import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.product.request.securityRequest.RegistrationRequest;

public interface IRegistrationService {

    String register(RegistrationRequest request, User user);

    Boolean confirmToken(String confirmationToken);

    String buildEmail(String name, String link);
}
