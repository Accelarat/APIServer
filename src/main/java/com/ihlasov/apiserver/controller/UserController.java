package com.ihlasov.apiserver.controller;

import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PostMapping("/store")
    public String storeJpg(@RequestParam MultipartFile file) {
        return service.storeJpg(file);
    }

    @PostMapping("/create")
    public Long createUser(
            @RequestParam String uri, @RequestParam String name, @RequestParam String email) {
        return service.createUser(uri, name, email);
    }
}
