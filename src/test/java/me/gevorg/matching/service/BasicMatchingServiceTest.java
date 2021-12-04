package me.gevorg.matching.service;

import me.gevorg.matching.model.Employee;
import me.gevorg.matching.model.EmployeeMatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasicMatchingServiceTest {
    @Autowired
    private BasicMatchingService service;

    @Test
    public void testGetScorePerfectMatch() {
        // Preparation.
        Employee left = new Employee("Valod", "valod@malod.am", "Lazy Folks", (short) 34, (byte) 4);
        Employee right = new Employee("Nina", "nina@mina.am", "Lazy Folks", (short) 29, (byte) 4);

        // Source.
        int score = service.getScore(left, right);

        // Assertion.
        assertEquals(100, score, "Score is not perfect!");
    }

    @Test
    public void testGetScoreMediocre() {
        // Preparation.
        Employee left = new Employee("Gevorg", "gevorg.ha@gmail.com", "Engineering", (short) 34, (byte) 4);
        Employee right = new Employee("Marine", "who@knows.am", "UX", (short) 25, (byte) 4);

        // Source.
        int score = service.getScore(left, right);

        // Assertion.
        assertEquals(40, score, "Score is not mediocre!");
    }

    @Test
    public void testGetScoreLow() {
        // Preparation.
        Employee left = new Employee("Samvel", "who@cares.com", "DJ", (short) 32, (byte) 3);
        Employee right = new Employee("Marine", "who@knows.am", "UX", (short) 25, (byte) 4);

        // Source.
        int score = service.getScore(left, right);

        // Assertion.
        assertEquals(0, score, "Score is not low!");
    }

    @Test
    public void testFindMatches() {
        // Preparation.
        List<Employee> employeeList = List.of(
                new Employee("Gabrielle Clarkson", "tamas@me_example.com", "Accounting", (short) 25, (byte) 2),
                new Employee("Zoe Peters", "gozer@icloud_example.com", "Finance", (short) 30, (byte) 3),
                new Employee("Jacob Murray", "lstein@me_example.com", "Accounting", (short) 22, (byte) 2),
                new Employee("Nicholas Vance", "saridder@outlook_example.com", "Engineering", (short) 27, (byte) -3)
        );

        // Source.
        List<EmployeeMatch> matches = service.findMatches(employeeList);

        // Assertions.
        List<EmployeeMatch> expected = List.of(
                new EmployeeMatch("Gabrielle Clarkson", "Jacob Murray", (byte) 100),
                new EmployeeMatch("Zoe Peters", "Nicholas Vance", (byte) 30)
        );

        assertEquals(expected, matches, "Matches are wrong!");
    }
}
