package com.filezila.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestWebController {
	
    @GetMapping(value = "/api/dimage")
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
 
        ClassPathResource imgFile = new ClassPathResource("image/delete.png");
 
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
    
    @GetMapping(value = "/api/eimage")
    public ResponseEntity<InputStreamResource> egetImage() throws IOException {
 
        ClassPathResource imgFile = new ClassPathResource("image/edit.png");
 
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
    
    
    @GetMapping(value = "/api/downimage")
    public ResponseEntity<InputStreamResource> dowloandgetImage() throws IOException {
 
        ClassPathResource imgFile = new ClassPathResource("image/download.jpg");
 
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}