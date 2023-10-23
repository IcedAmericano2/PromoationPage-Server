package com.example.promotionpage.domain.project.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.promotionpage.domain.project.dao.ProjectRepository;
import com.example.promotionpage.domain.project.domain.Project;
import com.example.promotionpage.domain.project.dto.request.CreateProjectServiceRequestDto;
import com.example.promotionpage.domain.project.dto.request.UpdatePostingStatusDto;
import com.example.promotionpage.domain.project.dto.request.UpdateProjectServiceRequestDto;
import com.example.promotionpage.global.common.response.ApiResponse;
import com.example.promotionpage.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

	private final ProjectRepository projectRepository;

	public ApiResponse createProject(CreateProjectServiceRequestDto dto) {
		Project project = dto.toEntity();
		Project savedProject = projectRepository.save(project);
		return ApiResponse.ok("프로젝트를 성공적으로 등록하였습니다.", savedProject);
	}

	public ApiResponse updateProject(UpdateProjectServiceRequestDto dto) {
		Optional<Project> optionalProject = projectRepository.findById(dto.projectId());
		if(optionalProject.isEmpty()){
			return ApiResponse.withError(ErrorCode.INVALID_PROJECT_ID);
		}

		Project project = optionalProject.get();
		Project updatedProject = project.update(dto);

		return ApiResponse.ok("프로젝트를 성공적으로 수정했습니다.", updatedProject);
	}

	public ApiResponse deleteNoticeBoard(Long projectId) {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if(optionalProject.isEmpty()){
			return ApiResponse.withError(ErrorCode.INVALID_PROJECT_ID);
		}

		Project project = optionalProject.get();
		projectRepository.delete(project);

		return ApiResponse.ok("프로젝트를 성공적으로 삭제했습니다.");
	}

	public ApiResponse retrieveAllProject() {
		List<Project> projectList = projectRepository.findAll();
		if (projectList.isEmpty()){
			return ApiResponse.ok("프로젝트가 존재하지 않습니다.");
		}

		return ApiResponse.ok("프로젝트 목록을 성공적으로 조회했습니다.", projectList);
	}

	public ApiResponse retrieveProject(Long projectId) {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if(optionalProject.isEmpty()){
			return ApiResponse.withError(ErrorCode.INVALID_PROJECT_ID);
		}

		Project project = optionalProject.get();
		return ApiResponse.ok("프로젝트를 성공적으로 조회했습니다.", project);
	}

	public ApiResponse updatePostingStatus(UpdatePostingStatusDto dto) {
		Optional<Project> optionalProject = projectRepository.findById(dto.projectId());
		if(optionalProject.isEmpty()){
			return ApiResponse.withError(ErrorCode.INVALID_PROJECT_ID);
		}

		Project project = optionalProject.get();
		Project updatedProject = project.updatePostingStatus(dto.isPosted());

		return ApiResponse.ok("프로젝트 게시 여부를 성공적으로 변경하였습니다.", updatedProject);

	}
}
