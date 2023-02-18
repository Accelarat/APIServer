package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.wrapper.FilesWrapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
@AllArgsConstructor
public class ImageService {
    private final FilesWrapper filesWrapper;

    @SneakyThrows
    public String storeJpg(MultipartFile file) {
        String root = "C:/jpg/";
        var fileName = file.getOriginalFilename();

        return filesWrapper.writeBytesIntoPath(Path.of(root + fileName), file.getBytes());
    }
}
