package com.example.promotionpage.global.adapter;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.promotionpage.global.common.response.ApiResponse;
import com.example.promotionpage.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
public class S3Uploader {

	private final AmazonS3Client amazonS3Client;

	@Value("${aws.s3.bucket}")
	private String bucket;


	public ApiResponse<String> uploadFile(MultipartFile multipartFile) {
		ObjectMetadata metadata = new ObjectMetadata();
		String fileName = UUID.randomUUID() + ".png";
		metadata.setContentLength(multipartFile.getSize());
		metadata.setContentType("image/png");
		try {
			amazonS3Client.putObject(bucket, fileName, multipartFile.getInputStream(), metadata);
			return ApiResponse.ok("이미지 업로드 성공", amazonS3Client.getUrl(bucket, fileName).toString());
		} catch (IOException e) {
			return ApiResponse.withError(ErrorCode.INTERNAL_SERVER_ERROR);
		}

	}
}