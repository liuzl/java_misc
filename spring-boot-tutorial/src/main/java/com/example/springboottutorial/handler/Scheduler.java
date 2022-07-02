package com.example.springboottutorial.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class Scheduler {

    @Scheduled(fixedDelay = 2000)
    public void run() {
        log.info(String.valueOf(new Date()));
    }
}
