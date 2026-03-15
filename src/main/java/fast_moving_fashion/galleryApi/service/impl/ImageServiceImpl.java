package fast_moving_fashion.galleryApi.service.impl;

import fast_moving_fashion.galleryApi.dto.ImageDto;
import fast_moving_fashion.galleryApi.dto.ImageUploadResponse;
import fast_moving_fashion.galleryApi.exception.InvalidRequestException;
import fast_moving_fashion.galleryApi.exception.ResourceNotFoundException;
import fast_moving_fashion.galleryApi.model.Image;
import fast_moving_fashion.galleryApi.repository.ImageRepository;
import fast_moving_fashion.galleryApi.service.ImageService;
import fast_moving_fashion.galleryApi.service.StorageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    private final StorageService storageService;
    private final ImageRepository imageRepository;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "webp");
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("image/jpeg", "image/png", "image/webp");
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    public ImageServiceImpl(StorageService storageService, ImageRepository imageRepository) {
        this.storageService = storageService;
        this.imageRepository = imageRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ImageDto> getAll() {
        return imageRepository.findAll(Sort.by(Sort.Direction.DESC, "uploadedAt"))
                .stream()
                .map(this::toImageDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ImageDto getById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image with id " + id + " not found"));

        return toImageDto(image);
    }

    @Transactional
    @Override
    public List<ImageUploadResponse> upload(List<MultipartFile> files) throws IOException {

        if (files == null || files.isEmpty()) {
            throw new InvalidRequestException("No images were received");
        }

        List<ImageUploadResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            validateFile(file);

            String uuid = UUID.randomUUID().toString();
            String extension = getExtension(file.getOriginalFilename());

            String originalPath = "originals/" + uuid + "." + extension;
            String originalUrl = storageService.uploadFile(file.getBytes(), file.getContentType(), originalPath);

            byte[] thumbnail = compressImage(file.getInputStream(), 400);
            String thumbPath = "thumbnails/" + uuid + "_thumb." + extension;
            String thumbUrl = storageService.uploadFile(thumbnail, file.getContentType(), thumbPath);

            Image image = new Image(originalPath, originalUrl, thumbPath, thumbUrl, file.getOriginalFilename(), file.getSize(), file.getContentType());

            Image newImage = imageRepository.save(image);
            responses.add(toImageUploadResponse(newImage));
        }

        return responses;
    }

    @Transactional
    @Override
    public void delete(Set<Long> ids) {
        List<Image> images = imageRepository.findAllById(ids);

        if (images.isEmpty()) return;

        images.forEach(image -> {
            storageService.deleteFile(image.getOriginalPath());
            storageService.deleteFile(image.getThumbnailPath());
        });

        imageRepository.deleteAll(images);
    }

    // ------------------
    private byte[] compressImage(InputStream inputStream, int maxSize) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Thumbnails.of(inputStream)
                .size(maxSize, maxSize)
                .outputQuality(0.75)
                .toOutputStream(os);

        return os.toByteArray();
    }

    private String getExtension(String filename) {
        return filename != null && filename.contains(".") ? filename.substring(filename.lastIndexOf('.') + 1) : "jpg";
    }

    private ImageDto toImageDto(Image image) {
        return new ImageDto(
                image.getId(),
                image.getOriginalUrl(),
                image.getThumbnailUrl(),
                image.getFilename(),
                image.getUploadedAt()
        );
    }

    private ImageUploadResponse toImageUploadResponse(Image image) {
        return new ImageUploadResponse(
                image.getId(),
                image.getOriginalUrl(),
                image.getThumbnailUrl());
    }

    private void validateFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String extension = getExtension(filename).toLowerCase();
        String mimeType = file.getContentType();

        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new InvalidRequestException(
                    "Invalid file type: '" + filename + "'. Allowed: jpg, jpeg, png, webp"
            );
        }

        if (mimeType == null || !ALLOWED_MIME_TYPES.contains(mimeType.toLowerCase())) {
            throw new InvalidRequestException(
                    "Invalid content type: '" + mimeType + "' for file '" + filename + "'"
            );
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new InvalidRequestException(
                    "File too large: '" + filename + "' (" + (file.getSize() / 1024 / 1024) + "MB). Max size: 10MB"
            );
        }
    }
}
