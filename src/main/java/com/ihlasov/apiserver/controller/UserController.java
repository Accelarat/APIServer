package com.ihlasov.apiserver.controller;

import com.ihlasov.apiserver.dto.ChangeStatusDTO;
import com.ihlasov.apiserver.dto.GetStatusDTO;
import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/statuses")
    public GetStatusDTO getStatuses(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime timestamp,
            @RequestParam String status) {
        return service.getStatuses(status, timestamp);
    }

    @PostMapping("/create")
    public Long createUser(@RequestParam String uri, @RequestParam String name, @RequestParam String email) {
        return service.createUser(uri, name, email);
    }

    @PostMapping("/changeStatus/{id}/{status}")
    public ChangeStatusDTO changeStatus(@PathVariable Long id, @PathVariable String status) {
        return service.changeStatus(id, status);
    }
}
