/**
 * @author sowmya.lingaiah
 */
package com.workflex.workation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WorkationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getWorkations_returnsOkWithData() throws Exception {
        mockMvc.perform(get("/workflex/workation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].workationId", notNullValue()))
                .andExpect(jsonPath("$[0].employee", notNullValue()))
                .andExpect(jsonPath("$[0].risk", notNullValue()));
    }

    @Test
    void getWorkations_containsAllCsvRows() throws Exception {
        mockMvc.perform(get("/workflex/workation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getWorkations_sortByEmployee_asc() throws Exception {
        mockMvc.perform(get("/workflex/workation")
                .param("sortBy", "employee")
                .param("sortDir", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employee", is("Andre Fischer")));
    }

    @Test
    void getWorkations_sortByEmployee_desc() throws Exception {
        mockMvc.perform(get("/workflex/workation")
                .param("sortBy", "employee")
                .param("sortDir", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employee", is("Steffen Jacobs")));
    }

    @Test
    void getWorkations_containsCorrectRiskLevels() throws Exception {
        mockMvc.perform(get("/workflex/workation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].risk", hasItems("HIGH", "LOW", "NO")));
    }

    @Test
    void getWorkations_sortByWorkingDays_asc() throws Exception {
        mockMvc.perform(get("/workflex/workation")
                .param("sortBy", "workingDays")
                .param("sortDir", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].workingDays", is(1)));
    }
}
