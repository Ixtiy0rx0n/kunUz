package org.example.kunuz.controller;

import org.example.kunuz.dto.ArticleTypeDTO;
import org.example.kunuz.dto.RegionDTO;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.RegionService;
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

    @PostMapping("/create")// Region Yaratish
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                            @RequestHeader(value = "profileID", required = true) Integer prId){
        if (!prId. equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        RegionDTO result =  regionService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RegionDTO> updateById(@RequestBody RegionDTO dto,
                                                @PathVariable("id") Integer id){
        return ResponseEntity.ok(regionService.updateById(id,dto));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id){
        regionService.deleteById(id);
        return true;
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<RegionDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseEntity.ok(regionService.getAll(page,size));

    }


    @GetMapping("/getByLang")
    public ResponseEntity<List<RegionDTO>> getByLang(@RequestParam(value = "lang", defaultValue = "uz")AppLanguage language){
        return ResponseEntity.ok(regionService.getByLang(language));
    }
}
