package fast_moving_fashion.galleryApi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String uploadFile(byte[] bytes, String contentType, String path) throws IOException;
    void deleteFile(String path);
    String getPublicUrl(String path);
}
