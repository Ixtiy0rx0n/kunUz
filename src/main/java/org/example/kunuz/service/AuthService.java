package org.example.kunuz.service;

import io.jsonwebtoken.JwtException;
import org.example.kunuz.dto.AuthDtO;
import org.example.kunuz.dto.JwtDTO;
import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.dto.RegistrationDTO;
import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.enums.ProfileStatus;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.EmailSendtarixiRepository;
import org.example.kunuz.repository.ProfileRepository;
import org.example.kunuz.util.JWTUtil;
import org.example.kunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendtarixiRepository emailSendtarixiRepository;

    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private SmsSenderService smsServerService;
    //    String code = RandomUtil.getRandomSmsCode();
    @Autowired
    private ResourceBundleService resourceBundleService;
    public ProfileDTO auth(AuthDtO profile, AppLanguage applanguage) { // login
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("email.password.wrong", applanguage));
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException(resourceBundleService.getMessage("profile.not.active", applanguage));
        }
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setRole(entity.getRole());
        dto.setSurname(entity.getSurname());
        dto.setJwt(JWTUtil.encode(entity.getEmail(), entity.getRole()));
        return dto;
    }

    public Boolean registration(RegistrationDTO dto, AppLanguage appLanguage) {
        // validation

        // check
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                profileRepository.delete(optional.get());// delete
                // or
                // send verification code (email/sms)
            } else {
                throw new AppBadException(resourceBundleService.getMessage("email.exists",appLanguage));
            }
        }
        LocalDateTime from = LocalDateTime.now().minusMinutes(1);
        LocalDateTime to = LocalDateTime.now();

        if (emailSendtarixiRepository.countSendEmail(dto.getEmail(), from, to) >= 3) {
            throw new AppBadException(resourceBundleService.getMessage("try.after.1.minute", appLanguage));
        }
        // create
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setPhone(dto.getPhone());

        profileRepository.save(entity);
        //send verification code (email/sms)
       /* String jwt = JWTUtil.encodeForEmail(entity.getId());

        String text = "<h1 style=\"text-align: center\">Hello %s</h1>\n" +
                "<p style=\"background-color: indianred; color: white; padding: 30px\">To complete registration please link to the following link</p>\n" +
                "<a style=\" background-color: #f44336;\n" +
                "  color: white;\n" +
                "  padding: 14px 25px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\" href=\"http://localhost:8080/auth/verification/email/%s\n" +
                "\">Click</a>\n" +
                "<br>\n";
        text = String.format(text, entity.getName(), jwt);
        mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);
        EmailSendHistoryEntity emailhistory = new EmailSendHistoryEntity();
        emailhistory.setId(entity.getId());
        emailhistory.setMessage(text);
        emailhistory.setCreatedDate(LocalDateTime.now());
        emailhistory.setEmail(dto.getEmail());
        emailSendtarixiRepository.save(emailhistory);
*/
//        String code ="";
        String code = ".";
        String text = "HUMOCARD *: popolnenie 12355640,00 UZS, 1000,00 Доллары США; " +
                "CLICK UZCARD HUMO P2P; 24-02-01;  Dostupno: 12477640,00 UZS";
        smsServerService.send(dto.getPhone(), text, code);
        return true;
    }

    public String emailVerification(String jwt, AppLanguage appLanguage) {
        try {
            JwtDTO jwtDTO = JWTUtil.decode(jwt);

            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (!optional.isPresent()) {
                throw new AppBadException(resourceBundleService.getMessage("profile.not.found",appLanguage ));
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException(resourceBundleService.getMessage("profile.wrong.status",appLanguage));
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException(resourceBundleService.getMessage("please.try.again", appLanguage));
        }
        return null;
    }

    public String smsVerification(String phone, String code) {
        return null;
    }
}