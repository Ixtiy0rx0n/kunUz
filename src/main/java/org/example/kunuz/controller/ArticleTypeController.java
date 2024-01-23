package org.example.kunuz.controller;

import org.example.kunuz.dto.ArticleTypeDTO;
import org.example.kunuz.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articletype")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("/create")// ArticleType Yaratish
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto){
        ArticleTypeDTO result =  articleTypeService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleTypeDTO> updateById(@RequestBody ArticleTypeDTO dto,
                                                     @PathVariable("id") Integer id){
        return ResponseEntity.ok(articleTypeService.updateById(id,dto));
    }
}
