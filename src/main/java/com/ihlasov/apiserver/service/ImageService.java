package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.wrapper.FilesWrapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final FilesWrapper filesWrapper;
    @Value("${files.root}")
    private String filesRoot;

    @SneakyThrows
    public String storeJpg(MultipartFile file) {
        var fileName = file.getOriginalFilename();

        return filesWrapper.writeBytesIntoPath(Path.of(filesRoot + fileName), file.getBytes());
    }
}
