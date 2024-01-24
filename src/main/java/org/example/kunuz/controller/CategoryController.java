package org.example.kunuz.controller;

import org.example.kunuz.dto.CategoryDTO;
import org.example.kunuz.service.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategorySevice categorySevice;

    @PostMapping("/create")// Category Yaratish
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto){
        CategoryDTO result =  categorySevice.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateById(@RequestBody CategoryDTO dto,
                                                  @PathVariable("id") Integer id){
        return ResponseEntity.ok(categorySevice.updateById(id,dto));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id){
        categorySevice.deleteById(id);
        return true;
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<CategoryDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseEntity.ok(categorySevice.getAll(page,size));

    }


    @GetMapping("/getByLang")
    public ResponseEntity<Optional<CategoryDTO>> getByLang(@RequestParam("id") Integer id,
                                                         @RequestParam("lang") String lang){
        return ResponseEntity.ok(categorySevice.getByLang(id,lang));
    }
}
