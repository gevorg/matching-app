package me.gevorg.matching.service;

import lombok.SneakyThrows;
import me.gevorg.matching.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service used for CSV data extraction.
 */
@Service
public class CSVService {
    private static final String[] HEADERS = { "Name", "Email", "Division", "Age", "Timezone" };
    private final CSVFormat csvFormat;

    public CSVService() {
        this.csvFormat = CSVFormat.Builder.create()
                .setSkipHeaderRecord(true)
                .setTrim(true)
                .setIgnoreHeaderCase(true)
                .setHeader(HEADERS)
                .build();
    }

    /**
     * Extracts data from CSV and returns employee list if valid input, else throws runtime exception.
     *
     * @param inputStream file input.
     * @return list of employees.
     */
    @SneakyThrows
    public List<Employee> extractFileData(InputStream inputStream) {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        CSVParser csvParser = new CSVParser(fileReader, csvFormat);

        return csvParser.getRecords().stream().map(
                record -> Employee.builder()
                        .name(record.get(HEADERS[0]))
                        .email(record.get(HEADERS[1]))
                        .division(record.get(HEADERS[2]))
                        .age(Short.valueOf(record.get(HEADERS[3])))
                        .timezone(Byte.valueOf(record.get(HEADERS[4])))
                        .build()
        ).collect(Collectors.toList());
    }
}
