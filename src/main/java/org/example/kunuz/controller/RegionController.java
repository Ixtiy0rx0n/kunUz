package org.example.kunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.kunuz.dto.ArticleTypeDTO;
import org.example.kunuz.dto.JwtDTO;
import org.example.kunuz.dto.RegionDTO;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.RegionService;
import org.example.kunuz.util.HttpRequestUtil;
import org.example.kunuz.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/adm/create")// Region Yaratish
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                            HttpServletRequest request){
        Integer id = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        /*Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        if (!role.equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/

        return ResponseEntity.ok(regionService.create(dto));
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<RegionDTO> updateById(@RequestBody RegionDTO dto,
                                                @PathVariable("id") Integer id,
                                                HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.updateById(id,dto));
    }

    @DeleteMapping("/adm/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        regionService.deleteById(id);
        return true;
    }

    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl<RegionDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                      HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(regionService.getAll(page,size));

    }


    @GetMapping("/getByLang")
    public ResponseEntity<List<RegionDTO>> getByLang(@RequestParam(value = "lang",defaultValue = "uz")
                                                         AppLanguage language){
        return ResponseEntity.ok(regionService.getByLang(language));
    }




}
