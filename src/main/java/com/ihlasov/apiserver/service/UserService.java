package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.dto.ChangeStatusDTO;
import com.ihlasov.apiserver.dto.GetStatusDTO;
import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Long createUser(String uri, String name, String email) {
        var newUser = User.builder()
                .uri(uri)
                .name(name)
                .status(true)
                .lastStatusChange(LocalDateTime.now())
                .email(email).build();

        var savedUser = repository.save(newUser);

        return savedUser.getId();
    }

    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    @SneakyThrows
    public ChangeStatusDTO changeStatus(Long id, Boolean status) {
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

    public GetStatusDTO getStatuses(Boolean status, LocalDateTime time) {
        return GetStatusDTO.builder()
                .requestId(time)
                .statuses(repository.findByStatusAndLastStatusChangeAfter(status, time))
                .build();
    }

    private int getRandomInRange(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }
}
