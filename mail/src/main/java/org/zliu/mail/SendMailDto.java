package org.zliu.mail;

import com.fasterxml.jackson.databind.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class SendMailDto implements Serializable {
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Email implements Serializable {
        String name;
        String email;
    }

    Email from;
    List<Email> tos;
    String subject;

    String templateName;
    List<String> tags;
    Map<String, String> templateData;
}
