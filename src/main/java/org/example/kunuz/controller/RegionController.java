package org.example.kunuz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kunuz.config.CustomUserDetails;
import org.example.kunuz.dto.RegionDTO;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.RegionService;
import org.example.kunuz.util.HttpRequestUtil;
import org.example.kunuz.util.SpringSecurityUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Tag(name = "region controller",description = "Regionlar ustida amallar")
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Region controller", description = "Region yaratish")
    @PostMapping("/adm/create")// Region Yaratish
    public ResponseEntity<RegionDTO> create(@NotNull @Valid @RequestBody RegionDTO dto,
                                            HttpServletRequest request){
//        Integer id = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR); jwt uchun
        /*Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        if (!role.equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        log.info("Region created {}",dto.getNameUz());
        return ResponseEntity.ok(regionService.create(dto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Region update", description = "Region yangilash")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<RegionDTO> updateById(@Valid @RequestBody RegionDTO dto,
                                                @PathVariable("id") Integer id,
                                                HttpServletRequest request,
                                                @RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                                    AppLanguage appLanguage){
//        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_ADMIN);
        log.info("Region update {}",dto.getNameUz());
        return ResponseEntity.ok(regionService.updateById(id,dto, appLanguage));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Region delete", description = "Region o'chirish")
    @DeleteMapping("/adm/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              HttpServletRequest request,
                              @RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                  AppLanguage appLanguage){
//        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_ADMIN);
        log.info("Region deleted {}", id);
        regionService.deleteById(id, appLanguage);
        return true;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "All regions", description = "Hamma regionlar")
    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl<RegionDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                      HttpServletRequest request){
//        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_ADMIN, ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(regionService.getAll(page,size));

    }
    @Operation(summary = "All region by language", description = "Barcha regionlar til bo'yicha")
    @GetMapping("/getByLang")
    public ResponseEntity<List<RegionDTO>> getByLang(@RequestParam(value = "lang",defaultValue = "uz")
                                                         AppLanguage language){
        return ResponseEntity.ok(regionService.getByLang(language));
    }
    @GetMapping("/change")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> change(){
        return ResponseEntity.ok("DONE");
    }

    @GetMapping("/change2")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ResponseEntity<String> change2() {
        return ResponseEntity.ok("DONE");
    }

}
