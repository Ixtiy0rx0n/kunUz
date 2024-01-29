package org.example.kunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.kunuz.dto.ArticleTypeDTO;
import org.example.kunuz.dto.JwtDTO;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.ArticleTypeService;
import org.example.kunuz.util.HttpRequestUtil;
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

    @PostMapping("/admin/create")// ArticleType Yaratish
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto,
                                                 HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        ArticleTypeDTO result =  articleTypeService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ArticleTypeDTO> updateById(@RequestBody ArticleTypeDTO dto,
                                                     @PathVariable("id") Integer id,
                                                     HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(articleTypeService.updateById(id,dto));
    }

    @DeleteMapping("/admin/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        articleTypeService.deleteById(id);
        return true;
    }

    @GetMapping("/admin/all")
    public ResponseEntity<PageImpl<ArticleTypeDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                           HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleTypeService.getAll(page,size));

    }

    @GetMapping("/getByLang")
    public ResponseEntity<Optional<ArticleTypeDTO>> getByLang(@RequestParam("id") Integer id,
                                                              @RequestParam("lang") String lang){
        return ResponseEntity.ok(articleTypeService.getByLang(id,lang));
    }
}
