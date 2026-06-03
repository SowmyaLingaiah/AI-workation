/**
 * @author sowmya.lingaiah
 */
package com.workflex.workation.service;

import com.workflex.workation.dto.WorkationDto;
import com.workflex.workation.model.Workation;
import com.workflex.workation.repository.WorkationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkationService {

    private final WorkationRepository workationRepository;

    public List<WorkationDto> getAllWorkations(String sortBy, String sortDir) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        String sortField = mapSortField(sortBy);
        Sort sort = Sort.by(direction, sortField);

        return workationRepository.findAll(sort)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private String mapSortField(String sortBy) {
        if (sortBy == null) return "workationId";
        return switch (sortBy) {
            case "workationId"  -> "workationId";
            case "employee"     -> "employee";
            case "origin"       -> "origin";
            case "destination"  -> "destination";
            case "start"        -> "start";
            case "end"          -> "end";
            case "workingDays"  -> "workingDays";
            case "risk"         -> "risk";
            default             -> "workationId";
        };
    }

    private WorkationDto toDto(Workation w) {
        return WorkationDto.builder()
                .workationId(w.getWorkationId())
                .employee(w.getEmployee())
                .origin(w.getOrigin())
                .destination(w.getDestination())
                .start(w.getStart())
                .end(w.getEnd())
                .workingDays(w.getWorkingDays())
                .risk(w.getRisk())
                .build();
    }
}
