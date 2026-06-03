/**
 * @author sowmya.lingaiah
 */
package com.workflex.workation.service;

import com.workflex.workation.dto.WorkationDto;
import com.workflex.workation.model.RiskLevel;
import com.workflex.workation.model.Workation;
import com.workflex.workation.repository.WorkationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkationServiceTest {

    @Mock
    private WorkationRepository workationRepository;

    @InjectMocks
    private WorkationService workationService;

    private Workation workation1;
    private Workation workation2;

    @BeforeEach
    void setUp() {
        workation1 = Workation.builder()
                .workationId("w1")
                .employee("Steffen Jacobs")
                .origin("Germany")
                .destination("United States")
                .start(LocalDate.of(2024, 1, 2))
                .end(LocalDate.of(2024, 12, 31))
                .workingDays(65)
                .risk(RiskLevel.HIGH)
                .build();

        workation2 = Workation.builder()
                .workationId("w4")
                .employee("Andre Fischer")
                .origin("Germany")
                .destination("Greece")
                .start(LocalDate.of(2023, 5, 22))
                .end(LocalDate.of(2023, 6, 30))
                .workingDays(50)
                .risk(RiskLevel.LOW)
                .build();
    }

    @Test
    void getAllWorkations_returnsAllWorkations() {
        when(workationRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(workation1, workation2));

        List<WorkationDto> result = workationService.getAllWorkations("workationId", "asc");

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getWorkationId()).isEqualTo("w1");
        assertThat(result.get(1).getWorkationId()).isEqualTo("w4");
    }

    @Test
    void getAllWorkations_mapsFieldsCorrectly() {
        when(workationRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(workation1));

        List<WorkationDto> result = workationService.getAllWorkations("workationId", "asc");
        WorkationDto dto = result.get(0);

        assertThat(dto.getWorkationId()).isEqualTo("w1");
        assertThat(dto.getEmployee()).isEqualTo("Steffen Jacobs");
        assertThat(dto.getOrigin()).isEqualTo("Germany");
        assertThat(dto.getDestination()).isEqualTo("United States");
        assertThat(dto.getStart()).isEqualTo(LocalDate.of(2024, 1, 2));
        assertThat(dto.getEnd()).isEqualTo(LocalDate.of(2024, 12, 31));
        assertThat(dto.getWorkingDays()).isEqualTo(65);
        assertThat(dto.getRisk()).isEqualTo(RiskLevel.HIGH);
    }

    @Test
    void getAllWorkations_withDescSort_callsRepositoryWithDescSort() {
        when(workationRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(workation2, workation1));

        List<WorkationDto> result = workationService.getAllWorkations("employee", "desc");

        assertThat(result).hasSize(2);
        verify(workationRepository).findAll(Sort.by(Sort.Direction.DESC, "employee"));
    }

    @Test
    void getAllWorkations_withInvalidSortField_defaultsToWorkationId() {
        when(workationRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(workation1));

        workationService.getAllWorkations("invalidField", "asc");

        verify(workationRepository).findAll(Sort.by(Sort.Direction.ASC, "workationId"));
    }

    @Test
    void getAllWorkations_returnsEmptyList_whenNoWorkations() {
        when(workationRepository.findAll(any(Sort.class))).thenReturn(List.of());

        List<WorkationDto> result = workationService.getAllWorkations("workationId", "asc");

        assertThat(result).isEmpty();
    }
}
