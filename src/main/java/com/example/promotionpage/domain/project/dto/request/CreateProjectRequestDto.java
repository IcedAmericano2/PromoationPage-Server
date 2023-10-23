package com.example.promotionpage.domain.project.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateProjectRequestDto(
	@Schema(description = "부서명, 빈 값/공백/null 을 허용하지 않습니다.")
	@NotBlank(message = "부서명은 필수 값입니다.")
	String department,

	@Schema(description = "카테고리, 빈 값/공백/null 을 허용하지 않습니다.")
	@NotBlank(message = "카테고리는 필수 값입니다.")
	String category,

	@Schema(description = "프로젝트 이름, 빈 값/공백/null 을 허용하지 않습니다.")
	@NotBlank(message = "프로젝트 이름은 필수 값입니다.")
	String name,

	@Schema(description = "클라이언트 이름, 빈 값/공백/null 을 허용하지 않습니다.")
	@NotBlank(message = "클라이언트 이름은 필수 값입니다.")
	String client,

	@Schema(description = "빈 값/공백/null 을 허용하지 않습니다.")
	@NotBlank(message = "date는 필수 값입니다.")
	String date,

	@Schema(description = "빈 값/공백/null 을 허용하지 않습니다.")
	@NotBlank(message = "link는 필수 값입니다.")
	String link,

	@Schema(description = "overView는 빈 값/공백/null 을 허용하지 않습니다.")
	@NotBlank(message = "overView는 필수 값입니다.")
	String overView
) {
	public CreateProjectServiceRequestDto toServiceRequest() {
		return new CreateProjectServiceRequestDto(department,category,name,client,date,link,overView);
	}
}
