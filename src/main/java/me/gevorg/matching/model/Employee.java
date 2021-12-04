package me.gevorg.matching.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private String email;
    private String division;
    private Short age;
    private Byte timezone;
}
