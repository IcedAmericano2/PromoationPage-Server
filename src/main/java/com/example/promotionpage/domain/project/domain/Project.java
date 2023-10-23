package com.example.promotionpage.domain.project.domain;

import java.sql.Blob;

import com.example.promotionpage.domain.project.dto.request.UpdateProjectServiceRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String department;

	private String category;

	private String name;

	private String client;

	private String date;

	private String link;

	private String overView;

	private Boolean isPosted;

	@Builder
	public Project(String department, String category, String name, String client, String date, String link,
		String overView) {
		this.department = department;
		this.category = category;
		this.name = name;
		this.client = client;
		this.date = date;
		this.link = link;
		this.overView = overView;
		this.isPosted = false;
	}

	public Project update(UpdateProjectServiceRequestDto dto) {
		this.department = dto.department();
		this.category = dto.category();
		this.name = dto.name();
		this.client = dto.client();
		this.date = dto.date();
		this.link = dto.link();
		this.overView = dto.overView();
		return this;
	}

	public Project updatePostingStatus(Boolean isPosted) {
		this.isPosted = isPosted;
		return this;
	}
}
