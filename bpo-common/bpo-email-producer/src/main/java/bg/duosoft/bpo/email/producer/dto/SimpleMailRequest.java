package bg.duosoft.bpo.email.producer.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * User: ggeorgiev
 * Date: 25.02.2025
 * Time: 14:52
 */
@Getter
@SuperBuilder
@ToString
public class SimpleMailRequest extends MailBase {
    private String message;
    private String subject;
    private boolean isHtml;
}
