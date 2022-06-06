package com.org.custom_springboot_template.service.concrete;

import com.org.custom_springboot_template.core.enums.CoreEnumExceptionMessages;
import com.org.custom_springboot_template.core.exceptions.exceptionModels.UnSuccessfulException;
import com.org.custom_springboot_template.core.utilities.outSourceServiceAdapter.abstracts.IEmailCheckerService;
import com.org.custom_springboot_template.core.utilities.outSourceServiceAdapter.abstracts.IEmailSenderService;
import com.org.custom_springboot_template.entity.primary.User;
import com.org.custom_springboot_template.entity.security.ConfirmationToken;
import com.org.custom_springboot_template.product.constants.ProductConstantApplicationProperties;
import com.org.custom_springboot_template.product.request.securityRequest.RegistrationRequest;
import com.org.custom_springboot_template.service.abstracts.IAccessTokenService;
import com.org.custom_springboot_template.service.abstracts.IConfirmationTokenService;
import com.org.custom_springboot_template.service.abstracts.IRefreshTokenService;
import com.org.custom_springboot_template.service.abstracts.IRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationService implements IRegistrationService {

    private final String logTitle = "RegistrationService : -> ";

    private final IEmailSenderService emailSenderService;
    private final IEmailCheckerService emailCheckerService;
    private final IConfirmationTokenService confirmationTokenService;
    private final IAccessTokenService accessTokenService;
    private final IRefreshTokenService refreshTokenService;

    public RegistrationService(IEmailSenderService emailSenderService,
                               IEmailCheckerService emailCheckerService,
                               IConfirmationTokenService confirmationTokenService,
                               IAccessTokenService accessTokenService,
                               IRefreshTokenService refreshTokenService) {
        this.emailSenderService = emailSenderService;
        this.emailCheckerService = emailCheckerService;
        this.confirmationTokenService = confirmationTokenService;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
    }


    @Override
    public String register(RegistrationRequest request, User user) {

        if (emailCheckerService.checkEMailAddress(request.getEmail())) {

            log.info(logTitle+"email is checked true");

            ConfirmationToken token = confirmationTokenService.createConfirmationToken(user);

            log.info(logTitle+"confirmation token created");

            confirmationTokenService.saveConfirmationToken(token);

            log.info(logTitle+"confirmation,on token saved");

            String link = ProductConstantApplicationProperties.MAIL_SEND_LINK + token.getConfirmationToken();

            log.info(logTitle+"email link fetched : "+link);

            emailSenderService.sendSimpleMessage(request.getEmail(),
                    "click the link for  register your account now!!!",
                    buildEmail(request.getUsername(), link)
            );

            log.info(logTitle+"email sent to user mail address");

            return token.getConfirmationToken();

        } else {

            log.error(logTitle+"email address is not valid");

            throw new NotValidException(NOT_VALID_E_MAIL_ADDRESS, "email not valid");
        }
    }

    @Override
    public Boolean confirmToken(String confirmationToken) {

        if (confirmationTokenService.verifyConfirmationToken(confirmationToken)) {

            log.info(logTitle+"confirmation token verified");

            return confirmationTokenService.setConfirmedAt(confirmationToken);
        } else {

            log.error(logTitle+"confirmation token not verified");

            throw new UnSuccessfulException(CoreEnumExceptionMessages.UN_SUCCESSFUL_CONFIRMATION_TOKEN_CONFIRMED,
                    "confirmation token cant confirmed");
        }
    }

    @Override
    public String buildEmail(String name, String link) {

        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
