package org.example.kunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.kunuz.dto.CategoryDTO;
import org.example.kunuz.dto.JwtDTO;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.CategorySevice;
import org.example.kunuz.util.HttpRequestUtil;
import org.example.kunuz.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategorySevice categorySevice;

    @PostMapping("/create")// Category Yaratish
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto,
                                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        CategoryDTO result =  categorySevice.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateById(@Valid @RequestBody CategoryDTO dto,
                                                  @PathVariable("id") Integer id,
                                                  HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(categorySevice.updateById(id,dto));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id,
                              HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        categorySevice.deleteById(id);
        return true;
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<CategoryDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                        HttpServletRequest request){
        Integer requestId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN, ProfileRole.MODERATOR);
        return ResponseEntity.ok(categorySevice.getAll(page,size));

    }


    @GetMapping("/getByLang")
    public ResponseEntity<Optional<CategoryDTO>> getByLang(@RequestParam("id") Integer id,
                                                         @RequestParam("lang") String lang){
        return ResponseEntity.ok(categorySevice.getByLang(id,lang));
    }
}
