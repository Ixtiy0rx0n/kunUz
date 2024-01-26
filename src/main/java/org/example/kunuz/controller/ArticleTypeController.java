package org.example.kunuz.controller;

import org.example.kunuz.dto.ArticleTypeDTO;
import org.example.kunuz.dto.JwtDTO;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.ArticleTypeService;
import org.example.kunuz.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articletype")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("/create")// ArticleType Yaratish
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto,
                                                 @RequestHeader(value = "Authorization") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ArticleTypeDTO result =  articleTypeService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleTypeDTO> updateById(@RequestBody ArticleTypeDTO dto,
                                                     @PathVariable("id") Integer id,
                                                     @RequestHeader(value = "Authorization") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(articleTypeService.updateById(id,dto));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              @RequestHeader(value = "Authorization") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build().hasBody();
        }
        articleTypeService.deleteById(id);
        return true;
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<ArticleTypeDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                           @RequestHeader(value = "Authorization") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(articleTypeService.getAll(page,size));

    }

    @GetMapping("/getByLang")
    public ResponseEntity<Optional<ArticleTypeDTO>> getByLang(@RequestParam("id") Integer id,
                                                              @RequestParam("lang") String lang){
        return ResponseEntity.ok(articleTypeService.getByLang(id,lang));
    }
}
