package fast_moving_fashion.galleryApi.controller;

import fast_moving_fashion.galleryApi.dto.ImageDto;
import fast_moving_fashion.galleryApi.dto.ImageUploadResponse;
import fast_moving_fashion.galleryApi.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    @Operation(summary = "Get all images")
    public ResponseEntity<List<ImageDto>> getAll() {
        return ResponseEntity.ok(imageService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get image by id")
    public ResponseEntity<ImageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getById(id));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload images")
    public ResponseEntity<List<ImageUploadResponse>> upload(
            @RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.upload(files));
    }

    @DeleteMapping
    @Operation(summary = "Delete images")
    public ResponseEntity<Void> delete(@RequestBody Set<Long> ids) {
        imageService.delete(ids);
        return ResponseEntity.noContent().build();
    }
}
