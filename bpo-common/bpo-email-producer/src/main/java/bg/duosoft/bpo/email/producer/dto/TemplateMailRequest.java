package bg.duosoft.bpo.email.producer.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 25.02.2025
 * Time: 14:51
 */
@Getter
@ToString
@SuperBuilder
public class TemplateMailRequest extends MailBase {
    private String template;
    private Map<String, String> templateParams;
}
