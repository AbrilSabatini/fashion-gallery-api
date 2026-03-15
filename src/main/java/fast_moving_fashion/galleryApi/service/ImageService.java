package fast_moving_fashion.galleryApi.service;

import fast_moving_fashion.galleryApi.dto.ImageDto;
import fast_moving_fashion.galleryApi.dto.ImageUploadResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ImageService {
    List<ImageDto> getAll();
    ImageDto getById(Long id);

    List<ImageUploadResponse> upload(List<MultipartFile> files) throws IOException;
    void delete(Set<Long> ids);

}
