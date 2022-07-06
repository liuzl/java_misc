package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmailDto {
    private String fromEmail;
    private String firstName;
    private String lastName;
    private To to;

    @Getter
    @Setter
    public static class To {
        String email;
        String fullName;
    }
}
