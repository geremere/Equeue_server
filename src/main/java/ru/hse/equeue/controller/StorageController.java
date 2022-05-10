package ru.hse.equeue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.equeue.client.AmazonAwsS3Client;
import ru.hse.equeue.util.EndPoints;

@RestController
@RequiredArgsConstructor
public class StorageController {
    private final AmazonAwsS3Client s3Client;

    @GetMapping(EndPoints.GET_IMAGE_BY_NAME)
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String imageName) {
        byte[] data = s3Client.getFile(imageName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + imageName + "\"")
                .body(resource);
    }
}
