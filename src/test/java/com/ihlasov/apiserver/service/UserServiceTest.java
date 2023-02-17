package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.repository.UserRepository;
import com.ihlasov.apiserver.wrapper.FilesWrapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Path;
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
    @MockBean
    private FilesWrapper filesWrapper;

    Long userId = 1L;
    String uri = "C:/jpg/fileName";
    String name = "Peter";
    String email = "email@ooo.com";
    String oldStatus = "Offline";
    String newStatus = "Online";
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

    @SneakyThrows
    @Test
    void storeJpg() {
        //given
        String fileName = "fileName";
        byte[] fileBytes = new byte[1];
        MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName, "jpeg", fileBytes);
        String root = "C:/jpg/";

        //when
        when(filesWrapper.writeBytesIntoPath(Path.of(root + fileName), fileBytes)).thenReturn(root + fileName);

        //then
        assertEquals(root + fileName, service.storeJpg(multipartFile));
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
        long userId = 1L;
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
        when(repository.findUsersByStatusAndLastStatusChange(oldStatus, lastStatusChange)).thenReturn(List.of(user));
        var serviceResult = service.getStatuses(oldStatus, lastStatusChange);

        //then
        assertEquals(List.of(user), serviceResult.getStatuses());
        assertEquals(lastStatusChange, serviceResult.getRequestId());
    }
}