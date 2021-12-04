package me.gevorg.matching.service;

import me.gevorg.matching.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CSVServiceTest {
    @Autowired
    private CSVService service;

    @Test
    public void testExtractFileData() {
        // Preparation.
        InputStream is = getClass().getClassLoader().getResourceAsStream("data-short.csv");

        // Source.
        List<Employee> employeeList = service.extractFileData(is);

        // Assertion.
        assertEquals(4, employeeList.size(), "Employee list is not loaded!");
    }

    @Test
    public void testExtractFileDataInvalid() {
        // Preparation.
        InputStream is = getClass().getClassLoader().getResourceAsStream("question.jpg");

        // Source.
        assertThrows(IllegalArgumentException.class, () -> service.extractFileData(is));
    }
}
