package com.ihlasov.apiserver.wrapper;

import com.ihlasov.apiserver.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FilesWrapper {
    public String writeBytesIntoPath(Path path, byte[] bytes) {
        try {
            return Files.write(path, bytes).toString();
        } catch (IOException e) {
            throw new ServiceException("Ошибка при попытке сохранить файл");
        }
    }
}
