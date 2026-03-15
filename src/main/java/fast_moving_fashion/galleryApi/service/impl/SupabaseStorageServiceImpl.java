package fast_moving_fashion.galleryApi.service.impl;

import fast_moving_fashion.galleryApi.exception.StorageException;
import fast_moving_fashion.galleryApi.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class SupabaseStorageServiceImpl implements StorageService {
    private final WebClient webClient;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.bucket}")
    private String supabaseBucket;

    public SupabaseStorageServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String uploadFile(byte[] bytes, String contentType, String path) throws IOException {
        String fullPath = supabaseBucket + "/" + path;

        webClient.post()
                .uri("/object/" + fullPath)
                .header("Content-Type", contentType)
                .bodyValue(bytes)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(String.class)
                        .map(body -> new StorageException("Error uploading file: " + body))
                )
                .bodyToMono(String.class)
                .block();

        return getPublicUrl(path);
    }

    @Override
    public void deleteFile(String path) {
        String fullPath = supabaseBucket + "/" + path;

        webClient.delete()
                .uri("/object/" + fullPath)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .map(body -> new StorageException("Error deleting file: " + body))
                )
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public String getPublicUrl(String path) {
        return supabaseUrl + "/storage/v1/object/public/" + supabaseBucket + "/" + path;
    }
}
