package org.example.kunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.kunuz.dto.AttachDTO;
import org.example.kunuz.dto.ArticleDTO;
import org.example.kunuz.entity.ArticleEntity;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.repository.ArticleRepository;
import org.example.kunuz.service.ArticleService;
import org.example.kunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    /*public AttachDTO create(AttachDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setPublisher();
        articleRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }*/

    /*@PutMapping("/adm/update/{id}")
    public ResponseEntity<ArticleDTO> updateById(@RequestBody ArticleDTO dto,
                                                @PathVariable("id") String id,
                                                HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.updateById(id,dto));
    }*/
}
