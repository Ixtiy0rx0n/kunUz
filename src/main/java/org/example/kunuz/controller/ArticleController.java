package org.example.kunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kunuz.dto.ArticleCreateDTO;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.ArticleService;
import org.example.kunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/adm/create")// Region Yaratish
    public ResponseEntity<ArticleCreateDTO> create(@Valid @RequestBody ArticleCreateDTO dto,
                                            HttpServletRequest request){
        Integer id = HttpRequestUtil.getProfileId(request, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(articleService.create(dto,id));
    }
}
