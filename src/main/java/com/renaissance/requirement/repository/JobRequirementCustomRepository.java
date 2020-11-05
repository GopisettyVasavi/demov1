package com.renaissance.requirement.repository;

import java.util.List;

import com.renaissance.requirement.dto.RequirementDTO;
import com.renaissance.requirement.model.JobRequirementEntity;

public interface JobRequirementCustomRepository {
List<JobRequirementEntity> searchRequirements(RequirementDTO requirementDto);
}
