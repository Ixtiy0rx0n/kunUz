package org.example.kunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.kunuz.dto.CreatedProfileDTO;
import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.ProfileService;
import org.example.kunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileDTO dto,
                                                    HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(profileService.create(dto));
    }
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<ProfileDTO> updateAdmin(@Valid @RequestBody ProfileDTO dto,
                                                @PathVariable("id") Integer id,
                                                 HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.updateDetail(dto, id));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ProfileDTO> updateById(@Valid @RequestBody ProfileDTO dto,
                                                 @PathVariable("id") Integer id,
                                                 HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request);
        return ResponseEntity.ok(profileService.updateDetail(dto, requestId));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(profileService.getAll(page,size));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        profileService.delete(id);
        return true;
    }

}
