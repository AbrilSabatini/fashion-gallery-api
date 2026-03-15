package fast_moving_fashion.galleryApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_path")
    private String originalPath;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    private String filename;

    private Long size;

    @Column(name = "mime_type")
    private String mimeType;

    @CreationTimestamp
    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    public Image(String originalPath, String originalUrl, String thumbnailPath, String thumbnailUrl, String filename, Long size, String mimeType) {
        this.originalPath = originalPath;
        this.originalUrl = originalUrl;
        this.thumbnailPath = thumbnailPath;
        this.thumbnailUrl = thumbnailUrl;
        this.filename = filename;
        this.size = size;
        this.mimeType = mimeType;
    }
}
