package org.example.kunuz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kunuz.dto.CategoryDTO;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.CategorySevice;
import org.example.kunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Slf4j
@RestController
@Tag(name = "Category controller",description = "Category lar ustida amallar")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategorySevice categorySevice;
    @Operation(summary = "Category create", description = "Category yaratish")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")// Category Yaratish
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto,
                                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_ADMIN, ProfileRole.ROLE_MODERATOR);
        CategoryDTO result =  categorySevice.create(dto);
        log.info("Category created {}",dto.getNameUz());
        return ResponseEntity.ok(result);
    }
    @Operation(summary = "Category update", description = "Category yangilash")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateById(@Valid @RequestBody CategoryDTO dto,
                                                  @PathVariable("id") Integer id,
                                                  HttpServletRequest request,
                                                  @RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                                      AppLanguage appLanguage){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_ADMIN);
        log.info("Category updated {}",dto.getNameUz());
        return ResponseEntity.ok(categorySevice.updateById(id,dto,appLanguage));
    }
    @Operation(summary = "Category delete", description = "Category o'chirish")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_ADMIN);
        categorySevice.deleteById(id);
        log.info("Category deleted {}",id);
        return true;
    }
    @Operation(summary = "All Category", description = "Hamma category")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<PageImpl<CategoryDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                        HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_ADMIN, ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(categorySevice.getAll(page,size));

    }

    @Operation(summary = "All Category by Language", description = "Hamma Category til bo'yicha")
    @GetMapping("/getByLang")
    public ResponseEntity<Optional<CategoryDTO>> getByLang(@RequestParam("id") Integer id,
                                                         @RequestParam("lang") String lang,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                                               AppLanguage appLanguage){
        return ResponseEntity.ok(categorySevice.getByLang(id,lang,appLanguage));
    }
}
