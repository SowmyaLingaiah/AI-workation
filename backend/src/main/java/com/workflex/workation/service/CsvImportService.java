/**
 * @author sowmya.lingaiah
 */
package com.workflex.workation.service;

import com.opencsv.CSVReader;
import com.workflex.workation.model.RiskLevel;
import com.workflex.workation.model.Workation;
import com.workflex.workation.repository.WorkationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsvImportService implements ApplicationRunner {

    private final WorkationRepository workationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (workationRepository.count() > 0) {
            log.info("Workations already loaded, skipping CSV import.");
            return;
        }

        log.info("Importing workations from CSV...");
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(new ClassPathResource("workations.csv").getInputStream()))) {

            String[] line;
            boolean firstLine = true;

            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // skip header
                }

                RiskLevel risk = switch (line[7].trim()) {
                    case "HIGH" -> RiskLevel.HIGH;
                    case "LOW"  -> RiskLevel.LOW;
                    default     -> RiskLevel.NO;
                };

                Workation workation = Workation.builder()
                        .workationId(line[0].trim())
                        .employee(line[1].trim())
                        .origin(line[2].trim())
                        .destination(line[3].trim())
                        .start(LocalDate.parse(line[4].trim()))
                        .end(LocalDate.parse(line[5].trim()))
                        .workingDays(Integer.parseInt(line[6].trim()))
                        .risk(risk)
                        .build();

                workationRepository.save(workation);
            }

            log.info("Successfully imported {} workations.", workationRepository.count());
        }
    }
}
