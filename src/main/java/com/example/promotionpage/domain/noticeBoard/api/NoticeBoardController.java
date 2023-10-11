package com.example.promotionpage.domain.noticeBoard.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.promotionpage.domain.noticeBoard.application.NoticeBoardService;
import com.example.promotionpage.global.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Tag(name = "공지 사항 API", description = "공지 사항 등록 / 수정 / 삭제 / 조회")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeBoardController {

	private final NoticeBoardService noticeBoardService;


	@Operation(summary = "공지 사항 등록 API")
	@PostMapping("/notice-board")
	public ApiResponse createNoticeBoard(@RequestPart(required = true) MultipartFile file, @Valid @NotBlank String title){
		return noticeBoardService.createNoticeBoard(file, title);
	}


}
