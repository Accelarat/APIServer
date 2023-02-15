package com.ihlasov.apiserver.controller;

import com.ihlasov.apiserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping("/store")
    public String storeJpg(@RequestParam MultipartFile file) {
        return service.storeJpg(file);
    }

    @PostMapping("/create")
    public void createUser(
            @RequestParam String uri, @RequestParam String name, @RequestParam String email) {
        service.createUser(uri, name, email);
    }
}
