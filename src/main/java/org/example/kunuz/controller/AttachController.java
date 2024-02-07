package org.example.kunuz.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.kunuz.dto.AttachDTO;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.service.AttachService;
import org.example.kunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@Tag(name = "Attach Controller", description = "Attachlar ustida amallar")
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    /*@PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String fileName = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(fileName);
    }*/

    @PostMapping("/upload")
    @Operation( summary = "Attach upload", description = "Attach(file)ni upload qilish")
    public ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file) {
        AttachDTO dto = attachService.save(file);
        return ResponseEntity.ok().body(dto);
    }

    @Operation( summary = "Attach open", description = "Attach(file)ni ochish")
    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }
    @Operation( summary = "Attach open", description = "Attach(file)ni ochish")
    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.open_general(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }
    @Operation( summary = "Attach yuklash", description = "Attach(file)ni yuklash")
    @GetMapping("/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        return attachService.download(fileName);
    }

    @DeleteMapping("/delete/{filename}")
    @Operation( summary = "Attach delete", description = "Attach(file)ni o'chirish")
    public ResponseEntity<String> deleteFile(@PathVariable String filename){
        String message = "";

        try {
            boolean existed = attachService.delete(filename);

            if (existed) {
                message = "Delete the file successfully: " + filename;
                log.info("Attach deleted {} ",filename);
                return ResponseEntity.ok().body(message);
            }
            message = "The file does not exist!";
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            message = "Could not delete the file: " + filename + ". Error: " + e.getMessage();
            log.warn("Attach not deleted {}",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage()+" | "+message);
        }
    }
}

