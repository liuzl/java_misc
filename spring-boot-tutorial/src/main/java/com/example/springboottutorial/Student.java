package com.example.springboottutorial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
}
