package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.dto.ChangeStatusDTO;
import com.ihlasov.apiserver.dto.GetStatusDTO;
import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.repository.UserRepository;
import com.ihlasov.apiserver.wrapper.FilesWrapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final FilesWrapper filesWrapper;

    @SneakyThrows
    public String storeJpg(MultipartFile file) {
        String root = "C:/jpg/";
        var fileName = file.getOriginalFilename();

        return filesWrapper.writeBytesIntoPath(Path.of(root + fileName), file.getBytes());
    }

    public Long createUser(String uri, String name, String email) {
        var newUser = User.builder()
                .uri(uri)
                .name(name)
                .lastStatusChange(LocalDateTime.now())
                .email(email).build();

        var savedUser = repository.save(newUser);

        return savedUser.getId();
    }

    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    @SneakyThrows
    public ChangeStatusDTO changeStatus(Long id, String status) {
        var user = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Такого пользователя нет в базе данных"));
        var lastStatus = user.getStatus();

        user.setStatus(status);
        user.setLastStatusChange(LocalDateTime.now());

        repository.save(user);

        Thread.sleep(getRandomInRange(5000, 10000));
        return ChangeStatusDTO.builder()
                .id(user.getId())
                .oldStatus(lastStatus)
                .newStatus(status).build();
    }

    public GetStatusDTO getStatuses(String status, LocalDateTime time) {
        return GetStatusDTO.builder()
                .statuses(repository.findUsersByStatusAndLastStatusChange(status, time))
                .build();
    }

    private int getRandomInRange(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }
}
