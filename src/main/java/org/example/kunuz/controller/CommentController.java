package org.example.kunuz.controller;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kunuz.dto.CommentDTO;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Operation(summary = "Comment create", description = "Komentariya yaratish")
    @PostMapping("/create")// Region Yaratish
    public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentDTO dto){
        return ResponseEntity.ok(commentService.create(dto));
    }

    @Operation(summary = "Comment update", description = "Komentariyani yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDTO> updateById(@Valid @RequestBody CommentDTO dto,
                                                @PathVariable("id") Integer id,
                                                 @RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                                     AppLanguage appLanguage){
        return ResponseEntity.ok(commentService.update(id,dto,appLanguage));
    }

    @Operation(summary = "Comment delete", description = "Komentariyani o'chirish")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adm/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              @RequestHeader(value = "Accept-Language", defaultValue = "uz")
                              AppLanguage appLanguage){
        log.info("Comment deleted {}", id);
        commentService.delete(id,appLanguage);
        return true;
    }

   /* @Operation(summary = "Get Article comments", description = "Article dagi hamma komentariyalar")
    @GetMapping("/allarticlecomment/{id}")
    public ResponseEntity<List<CommentDTO>> getArticleComment(@PathVariable("id") String id){
        return ResponseEntity.ok(commentService.getArticleComment(id));
    }*/


}
