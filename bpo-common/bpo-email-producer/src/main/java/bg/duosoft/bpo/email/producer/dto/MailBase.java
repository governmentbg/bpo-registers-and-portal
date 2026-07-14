package bg.duosoft.bpo.email.producer.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 27.02.2025
 * Time: 15:31
 */
@SuperBuilder
@Getter
@ToString
public class MailBase {
    private List<String> recipients;
    private List<String> cc;
    private List<String> bcc;
    private List<Integer> attachmentIds;
}
