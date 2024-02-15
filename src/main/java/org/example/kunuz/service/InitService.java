package org.example.kunuz.service;

import org.example.kunuz.enums.ProfileStatus;
import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.repository.ProfileRepository;
import org.example.kunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InitService {
    @Autowired
    private ProfileRepository profileRepository;
    public void initAdmin() {
        String adminEmail = "xabibulloyevixtiyorxon@gmail.com";
        Optional<ProfileEntity> optional = profileRepository.findByEmail(adminEmail);
        if (optional.isPresent()) {
            return;
        }
        // create admin
        ProfileEntity admin = new ProfileEntity();
        admin.setName("Ixtiyorxon");
        admin.setSurname("Xabibulloyev");
        admin.setEmail(adminEmail);
        admin.setStatus(ProfileStatus.ACTIVE);
        admin.setRole(ProfileRole.ROLE_ADMIN);
        admin.setPassword(MDUtil.encode("12345"));
        profileRepository.save(admin);
    }

}