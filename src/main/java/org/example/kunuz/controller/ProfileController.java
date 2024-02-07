package org.example.kunuz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kunuz.dto.CreatedProfileDTO;
import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.ProfileService;
import org.example.kunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Tag(name = "Profile controller", description = "Profile ustida amallar")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Operation(summary = "Profile create",description = "Profile yaratish")
    @PostMapping("")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileDTO dto,
                                                    HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        log.info("Profile created {}",dto.getEmail());
        return ResponseEntity.ok(profileService.create(dto));
    }
    @Operation(summary = "Profile udpate only byAdmin",description = "Profile yangilash admin tomonida")
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<ProfileDTO> updateAdmin(@Valid @RequestBody ProfileDTO dto,
                                                @PathVariable("id") Integer id,
                                                 HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        log.info("Profile updateAdmin {}",dto.getEmail());
        return ResponseEntity.ok(profileService.updateDetail(dto, id));
    }
    @Operation(summary = "Profile update Any",description = "Profile yangilash hamma o'zi")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ProfileDTO> updateById(@Valid @RequestBody ProfileDTO dto,
                                                 @PathVariable("id") Integer id,
                                                 HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request);
        return ResponseEntity.ok(profileService.updateDetail(dto, requestId));
    }

    @Operation(summary = "All profiles",description = "Hamma profillar")
    @GetMapping("/all")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(profileService.getAll(page,size));
    }
    @Operation(summary = "profile delete",description = "profile o'chirish")
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        log.info("Profile deleted {}",requestId);
        profileService.delete(id);
        return true;
    }

}
