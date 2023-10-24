package com.example.promotionpage.domain.project.dto.request;

import java.util.LinkedList;

public record UpdateProjectServiceRequestDto(
	Long projectId,
	String department,
	String category,
	String name,
	String client,
	String date,
	String link,
	String overView
) {
}