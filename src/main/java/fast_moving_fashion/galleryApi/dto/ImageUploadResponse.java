package fast_moving_fashion.galleryApi.dto;

import lombok.*;

public record ImageUploadResponse(Long id, String originalUrl, String thumbUrl) {}
