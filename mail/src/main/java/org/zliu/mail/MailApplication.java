package org.zliu.mail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@SpringBootApplication
public class MailApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(String... args) throws JsonProcessingException {
        System.out.println("RUN");
        for (int i = 0; i < args.length; ++i) {
            System.out.println("args " + i + " " + args[i]);
        }

        SendMailDto dto = new SendMailDto();
        dto.templateName = "workout-follow-up";
        dto.tos = new ArrayList<>();
        dto.tos.add(new SendMailDto.Email("Zhanliang", "zhanliangliu@gmail.com"));
        dto.subject = "Here's your subject";
        dto.templateData = new HashMap<>();
        dto.templateData.put("first_name", "X Y");
        //MailUtils.send(dto);

        stringRedisTemplate.opsForZSet().add("mail_queue", "milo1", new Date().getTime());
        stringRedisTemplate.opsForValue().set("milo1", new ObjectMapper().writeValueAsString(dto));
    }
}
