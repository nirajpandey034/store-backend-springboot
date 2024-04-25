package storeBackend.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    public boolean uploadFile(MultipartFile multipartFile) {

        try {
            String IMAGE_DIR = "D:\\java-workspace\\storeBackend\\src\\main\\resources\\static\\images";
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(IMAGE_DIR + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
