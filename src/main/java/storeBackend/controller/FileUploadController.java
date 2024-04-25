package storeBackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import storeBackend.helper.FileUploadHelper;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @SuppressWarnings("null")
    @PostMapping("/upload-file")
    public ResponseEntity<?> uplaodFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please select a file");
        }

        else if (!file.getContentType().equals("image/jpeg")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please select a jpeg file");
        }
        FileUploadHelper fileUploadHelper = new FileUploadHelper();
        boolean fileStatus = fileUploadHelper.uploadFile(file);
        if (fileStatus)
            return ResponseEntity.ok().body("Upload Succesfull");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload Failed");
    }
}
