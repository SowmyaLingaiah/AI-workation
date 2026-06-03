/**
 * @author sowmya.lingaiah
 */
package com.workflex.workation.controller;

import com.workflex.workation.dto.WorkationDto;
import com.workflex.workation.service.WorkationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workflex")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Workation", description = "Workation management API")
public class WorkationController {

    private final WorkationService workationService;

    @GetMapping("/workation")
    @Operation(summary = "Get all workations", description = "Returns all workations, sortable by any column")
    public ResponseEntity<List<WorkationDto>> getAllWorkations(
            @RequestParam(defaultValue = "workationId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(workationService.getAllWorkations(sortBy, sortDir));
    }
}
