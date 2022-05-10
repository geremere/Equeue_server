package ru.hse.equeue.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.hse.equeue.exception.BaseException;
import ru.hse.equeue.exception.message.ExceptionMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AmazonAwsS3Client {

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    private final AmazonS3 S3Client;

    public String uploadFile(MultipartFile file) {
        String filePath = "image/" + file.getOriginalFilename() + "_" + System.currentTimeMillis();
        S3Client.putObject(new PutObjectRequest(bucketName, filePath, convertMultipartFileTpFile(file)));
        return filePath;
    }

    public byte[] getFile(String fileName) {
        S3Object s3Object = S3Client.getObject(bucketName, "image/" + fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new BaseException(ExceptionMessage.IMAGE_NOT_UPLOADED);
        }
    }


    private File convertMultipartFileTpFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream stream = new FileOutputStream(convertedFile)) {
            stream.write(file.getBytes());
        } catch (IOException ex) {
            throw new BaseException(ExceptionMessage.IMAGE_NOT_UPLOADED);
        }
        return convertedFile;
    }

}
