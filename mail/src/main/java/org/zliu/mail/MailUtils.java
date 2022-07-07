package org.zliu.mail;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MailUtils {

    private static final String MAIL_CHIMP_TEMPLATE_URL = "https://mandrillapp.com/api/1.0/messages/send-template";

    public static void send(SendMailDto dto) throws JsonProcessingException {
        if (dto == null) return;
        MailchimpMessage e = new MailchimpMessage();
        e.key = "YOUR KEY";
        e.templateName = dto.getTemplateName();
        e.message = new MailchimpMessage.Message();
        e.message.subject = dto.getSubject();
        e.message.fromEmail = dto.getFrom().email;
        e.message.fromName = dto.getFrom().name;
        e.message.to = dto.getTos();
        e.message.tags = dto.getTags();
        e.message.globalMergeVars = new ArrayList<>();
        dto.getTemplateData().forEach((s, s2) -> e.message.globalMergeVars.add(new MailchimpMessage.Var(s, s2)));

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(e);

        System.out.println(body);


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        HttpEntity<String> response = restTemplate.postForEntity(MAIL_CHIMP_TEMPLATE_URL, httpEntity, String.class);
        String responseBody = response.getBody();
        System.out.println(responseBody);

        JsonNode obj = new ObjectMapper().readTree(responseBody);
        if (obj == null || obj.size() == 0 || !StringUtils.equals(obj.get(0).get("status").toString(), "\"sent\"")) {
            log.error("Send mail failed by mailchimp, response={}", responseBody);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class MailchimpMessage {
        @Getter
        @Setter
        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Message {
            String mergeLanguage = "handlebars";
            String subject;
            String fromEmail;
            String fromName;
            List<SendMailDto.Email> to;
            List<String> tags;
            List<Var> globalMergeVars;
        }

        @AllArgsConstructor
        @Getter
        @Setter
        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Var {
            String name;
            String content;
        }


        String key;
        String templateName;
        List<Object> templateContent = new ArrayList<>();
        Message message;
    }
}
