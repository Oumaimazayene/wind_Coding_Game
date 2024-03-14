package com.demo.demo.ServiceImpl;

import com.demo.demo.Service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class ImageUploadServiceImpl  implements ImageUploadService {


    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String saveImage(MultipartFile imageFile) throws IOException {
        String originalFileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        File file = resourceLoader.getResource("resources/static/").getFile();
        String uploadDir = file.getAbsolutePath() + File.separator + "image";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path filePath = Paths.get(uploadDir, originalFileName);
        Files.write(filePath, imageFile.getBytes());

        return filePath.toString();
    }
}

