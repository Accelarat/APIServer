package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.dto.ChangeStatusDTO;
import com.ihlasov.apiserver.dto.GetStatusDTO;
import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    @SneakyThrows
    public String storeJpg(MultipartFile file) {
        String root = "C:/jpg/";
        var fileName = file.getOriginalFilename();

        var fileOnServer = Files.write(Path.of(root + fileName), file.getBytes());

        return fileOnServer.toString();
    }

    public Long createUser(String uri, String name, String email) {
        var newUser = User.builder()
                .uri(uri)
                .name(name)
                .email(email).build();

        var savedUser = repository.save(newUser);

        return savedUser.getId();
    }

    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    public ChangeStatusDTO changeStatus(Long id, String status) {
        var user = repository.findById(id).orElseThrow(() -> new RuntimeException("Такого пользователя нет в базе данных"));

        var lastStatus = user.getStatus();

        user.setStatus(status);

        repository.save(user);

        return ChangeStatusDTO.builder()
                .id(user.getId())
                .oldStatus(lastStatus)
                .newStatus(status).build();
    }

    public GetStatusDTO getStatus() {
        return GetStatusDTO.builder()
                .statuses(repository.findAll())
                .build();
    }
}
