package com.example.promotionpage.domain.noticeBoard.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.promotionpage.domain.noticeBoard.dao.NoticeBoardRepository;
import com.example.promotionpage.domain.noticeBoard.domain.NoticeBoard;
import com.example.promotionpage.global.adapter.S3Uploader;
import com.example.promotionpage.global.common.response.ApiResponse;
import com.example.promotionpage.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeBoardService {

	private final NoticeBoardRepository noticeBoardRepository;
	private final S3Uploader s3Uploader;

	public ApiResponse createNoticeBoard(MultipartFile file, String title) {
		String imageUrl = uploadImage(file).getData();

		NoticeBoard noticeBoard = NoticeBoard.builder()
			.title(title)
			.imageUrl(imageUrl)
			.build();

		noticeBoardRepository.save(noticeBoard);
		return ApiResponse.ok("공지 사항을 성공적으로 등록하였습니다.");
	}

	private ApiResponse<String> uploadImage(MultipartFile multipartFile) {
		if (multipartFile == null) {
			return ApiResponse.withError(ErrorCode.NOT_EXIST_IMAGE_FILE, null);
		}
		return s3Uploader.uploadFile(multipartFile);
	}

}
