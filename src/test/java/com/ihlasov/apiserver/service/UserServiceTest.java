package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserService.class)
class UserServiceTest {

    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    Long userId = 1L;
    String uri = "C:/jpg/fileName";
    String name = "Peter";
    String email = "email@ooo.com";
    Boolean oldStatus = false;
    Boolean newStatus = true;
    LocalDateTime lastStatusChange = LocalDateTime.of(2023, 2, 18, 0, 6);
    User user;

    @Test
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(userId)
                .uri(uri)
                .name(name)
                .email(email)
                .status(oldStatus)
                .lastStatusChange(lastStatusChange).build();
    }

    @Test
    void createUser() {
        //when
        when(repository.save(any(User.class))).thenReturn(user);

        //then
        assertEquals(1L, service.createUser(uri, name, email));
    }

    @Test
    void getUser() {
        //given
        var user = new User();

        //when
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        //then
        assertEquals(Optional.of(user), service.getUser(1L));
    }

    @Test
    void changeStatus() {
        //when
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        var serviceResult = service.changeStatus(userId, newStatus);

        //then
        assertEquals(oldStatus, serviceResult.getOldStatus());
        assertEquals(newStatus, serviceResult.getNewStatus());
        verify(repository).save(any());
    }

    @Test
    void getStatuses() {
        //when
        when(repository.findByStatusAndLastStatusChangeAfter(oldStatus, lastStatusChange)).thenReturn(List.of(user));
        var serviceResult = service.getStatuses(oldStatus, lastStatusChange);

        //then
        assertEquals(List.of(user), serviceResult.getStatuses());
        assertEquals(lastStatusChange, serviceResult.getRequestId());
    }
}