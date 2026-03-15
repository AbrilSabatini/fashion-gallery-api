package fast_moving_fashion.galleryApi.dto;

import fast_moving_fashion.galleryApi.model.Image;
import lombok.*;

import java.time.LocalDateTime;

@Builder
public record ImageDto(Long id, String originalUrl, String thumbnailUrl, String filename, LocalDateTime uploadedAt) {}
