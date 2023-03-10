package com.ihlasov.apiserver.service;

import com.ihlasov.apiserver.wrapper.FilesWrapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ImageService.class)
class ImageServiceTest {

    @Autowired
    private ImageService service;

    @MockBean
    private FilesWrapper filesWrapper;

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
}