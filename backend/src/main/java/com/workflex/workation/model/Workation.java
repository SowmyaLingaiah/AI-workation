/**
 * @author sowmya.lingaiah
 */
package com.workflex.workation.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "workations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workation {

    @Id
    @Column(name = "workation_id")
    private String workationId;

    @Column(nullable = false)
    private String employee;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDate start;

    @Column(name = "end_date", nullable = false)
    private LocalDate end;

    @Column(name = "working_days", nullable = false)
    private Integer workingDays;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskLevel risk;
}
