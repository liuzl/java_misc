package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws JsonProcessingException {
        log.info(args.length + " args");
        EmailDto dto = new EmailDto();
        dto.setFirstName("cx@sf.cc");
        dto.setFirstName("Zhangxin");
        dto.setLastName("Liu");
        dto.setTo(new EmailDto.To());
        dto.getTo().email = "billg@microsoft.com";
        dto.getTo().fullName = "Bill Gates";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        String json = mapper.writeValueAsString(dto);
        log.info("json: " + json);
    }
}
