package me.gevorg.matching.controller;

import lombok.extern.slf4j.Slf4j;
import me.gevorg.matching.model.Employee;
import me.gevorg.matching.model.EmployeeMatch;
import me.gevorg.matching.service.CSVService;
import me.gevorg.matching.service.MatchingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Matching API.
 */
@Slf4j
@RestController
@RequestMapping("matching")
public class MatchingController {
    private final CSVService csvService;
    private final MatchingService matchingService;

    public MatchingController(CSVService csvService, MatchingService matchingService) {
        this.csvService = csvService;
        this.matchingService = matchingService;
    }

    /**
     * CSV file upload handler.
     *
     * @param file CSV file.
     * @return list of matches.
     * @throws IOException if there was a problem with file source.
     */
    @PostMapping
    public List<EmployeeMatch> upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Upload file {}", file.getOriginalFilename());

        List<Employee> employeeList = csvService.extractFileData(file.getInputStream());
        log.info("Employee list {}", employeeList);

        List<EmployeeMatch> employeeMatches = matchingService.findMatches(employeeList);
        log.info("Best matches found {}", employeeMatches);

        return employeeMatches;
    }
}
