package ru.smartbudject.crmbackend.utils;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class UploadFileUtil {
    @Value("${upload.path}")
    private String uploadFilePath;

    /**
     * Сохранение файла в папку хранилища с уникальным именем
     * @param file файл от клиента
     * @return новое уникальное имя файла, под котором произошло сохранение
     * @throws IOException
     */
    public String saveFile(@NotNull MultipartFile file) throws IOException {
        String newFileName = UUID.randomUUID() + "." + file.getOriginalFilename();
        File newFile = new File(uploadFilePath, newFileName);
        file.transferTo(newFile);
        return newFileName;
    }

}
