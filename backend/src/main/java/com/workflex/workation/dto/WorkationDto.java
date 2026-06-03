/**
 * @author sowmya.lingaiah
 */
package com.workflex.workation.dto;

import com.workflex.workation.model.RiskLevel;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkationDto {
    private String workationId;
    private String employee;
    private String origin;
    private String destination;
    private LocalDate start;
    private LocalDate end;
    private Integer workingDays;
    private RiskLevel risk;
}
