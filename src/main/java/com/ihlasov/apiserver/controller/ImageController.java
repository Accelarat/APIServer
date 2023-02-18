package com.ihlasov.apiserver.controller;

import com.ihlasov.apiserver.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageService service;

    @PostMapping
    public String storeJpg(@RequestParam MultipartFile file) {
        return service.storeJpg(file);
    }
}
