package com.ihlasov.apiserver.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
@AllArgsConstructor
public class UserService {

    @SneakyThrows
    public String storeJpg(MultipartFile file) {
        String root = "C:/jpg/";
        var fileName = file.getOriginalFilename();

        var fileOnServer = Files.write(Path.of(root + fileName), file.getBytes());

        return fileOnServer.toString();
    }

    public void createUser(String uri, String name, String email) {
    }
}
