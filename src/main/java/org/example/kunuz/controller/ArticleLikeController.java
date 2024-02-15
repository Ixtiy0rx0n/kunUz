package org.example.kunuz.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.kunuz.dto.ArticleLikeDTO;
import org.example.kunuz.service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articlelike")
public class ArticleLikeController {
    @Autowired
    private ArticleLikeService articleLikeService;
   /* @Operation(summary = "article like or dislike controller", description = "article ga like yoki dislike bosish")
    @PostMapping("/likeordislike")// Region Yaratish
    public ResponseEntity<ArticleLikeDTO> create(@Valid @RequestBody ArticleLikeDTO dto){
        return ResponseEntity.ok(articleLikeService.likeOrDislike(dto));
    }*/

    @Operation(summary = "article like or dislike remove controller", description = "article ga like yoki dislikeni olib tashlash")
    @PostMapping("/likeordislikeremove")// Region Yaratish
    public ResponseEntity<Boolean> removeLike(@Valid @RequestBody ArticleLikeDTO dto){
        return ResponseEntity.ok(articleLikeService.removeLike(dto));
    }
}
