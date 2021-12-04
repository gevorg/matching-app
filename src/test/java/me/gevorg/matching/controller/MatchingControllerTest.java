package me.gevorg.matching.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gevorg.matching.model.Employee;
import me.gevorg.matching.model.EmployeeMatch;
import me.gevorg.matching.service.CSVService;
import me.gevorg.matching.service.MatchingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MatchingControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CSVService csvService;

    @MockBean
    private MatchingService matchingService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testUpload() throws Exception {
        // Preparation.
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
                "text/csv", "Some Odd Data".getBytes());

        List<Employee> employeeList = new ArrayList<>();
        when(csvService.extractFileData(any(InputStream.class))).thenReturn(employeeList);

        List<EmployeeMatch> matchList = List.of(
                new EmployeeMatch("Who", "Cares", (byte) 100)
        );
        when(matchingService.findMatches(employeeList)).thenReturn(matchList);

        // Source.
        this.mvc.perform(multipart("/matching").file(multipartFile))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(matchList)));

        // Expectations.
        verify(csvService, times(1)).extractFileData(any(InputStream.class));
        verify(matchingService, times(1)).findMatches(employeeList);
    }
}
